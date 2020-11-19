package com.example.appexcerse.area.admin.chucNang.Product;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.appexcerse.R;
import com.example.appexcerse.dao.ProductDAO;
import com.example.appexcerse.model.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentModifyProduct#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentModifyProduct extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Product curentProduct;
    private ImageView productImg;
    private EditText txtName;
    private EditText txtCostOfGoodsSold;
    private EditText txtSalePrice;
    private EditText txtQuantity;
    private EditText txtDescription;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnUploadImage;
    private Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    public FragmentModifyProduct() {
        // Required empty public constructor
    }

    public FragmentModifyProduct(Product product) {
        curentProduct = product;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentModifyProduct.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentModifyProduct newInstance(String param1, String param2) {
        FragmentModifyProduct fragment = new FragmentModifyProduct();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modify_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        productImg = view.findViewById(R.id.productImg);
        txtName = view.findViewById(R.id.txtName);
        txtDescription = view.findViewById(R.id.txtDescription);
        txtCostOfGoodsSold = view.findViewById(R.id.txtCostOfGoodsSold);
        txtSalePrice = view.findViewById(R.id.txtSalePrice);
        txtQuantity = view.findViewById(R.id.txtQuantity);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnUploadImage = view.findViewById(R.id.btnUploadImage);

        txtName.setText(curentProduct.getName());
        txtCostOfGoodsSold.setText(String.valueOf(curentProduct.getCostOfGoodSold()));
        txtSalePrice.setText(String.valueOf(curentProduct.getSalePrice()));
        txtDescription.setText(curentProduct.getDescriptionl());
        Glide.with(view).load(curentProduct.getImgUrl()).into(productImg);
        txtQuantity.setText(String.valueOf(curentProduct.getQuantiy()));

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ProductDAO().delete(curentProduct.getId());
                getFragmentManager().popBackStack();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curentProduct.setQuantiy(Integer.valueOf(txtQuantity.getText().toString()));
                curentProduct.setCostOfGoodSold(Float.valueOf(txtCostOfGoodsSold.getText().toString()));
                curentProduct.setDescriptionl(txtDescription.getText().toString());
                curentProduct.setName(txtName.getText().toString());
                curentProduct.setSalePrice(Float.valueOf(txtSalePrice.getText().toString()));
                new ProductDAO().push(curentProduct);
            }
        });
    }


    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            uploadPicture();
        }
    }

    private void uploadPicture() {

        final String randomKey = UUID.randomUUID().toString();
        final StorageReference imgUrl = storageReference.child("Image/Product/" + randomKey + ".jpg");

        imgUrl.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        imgUrl.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                curentProduct.setImgUrl(uri.toString());
                                productImg.setImageURI(imageUri);
                            }
                        });
                        FancyToast.makeText(getContext(),"Upload Successful",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        FancyToast.makeText(getContext(),"Failed",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                    }
                });
    }


}