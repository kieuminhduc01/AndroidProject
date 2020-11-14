package com.example.appexcerse.area.admin.chucNang.Excercise;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.appexcerse.R;
import com.example.appexcerse.area.admin.logicUtil.Util;
import com.example.appexcerse.constant.listModel.ListLevel;
import com.example.appexcerse.constant.listModel.ListMucDichTap;
import com.example.appexcerse.constant.listModel.ListMuscleFocus;
import com.example.appexcerse.constant.listModel.ListTypeOfExcercise;
import com.example.appexcerse.dao.ExerciseDAO;
import com.example.appexcerse.model.Exercise;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentModifyExcersice#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentModifyExcersice extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Exercise currentExercise;
    private ChipGroup chipMucDichTap;
    private ChipGroup chipForcusMucle;
    private ChipGroup chipTypeOfExcercise;
    private Button btnUpdate;
    private Button btnDelete;
    private EditText txtName;
    private EditText txtEquipment;
    private ImageView imgExercise;
    private TextView txtVideoUrl;
    private TextView txtCaloriesPerRep;
    private Spinner spinnerLevel;
    private Uri imageUri;
    private Uri videoUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    public FragmentModifyExcersice() {
        // Required empty public constructor
    }

    public FragmentModifyExcersice(Exercise exercise) {
        // Required empty public constructor
        this.currentExercise = exercise;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentModifyExcersice.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentModifyExcersice newInstance(String param1, String param2) {
        FragmentModifyExcersice fragment = new FragmentModifyExcersice();
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
        return inflater.inflate(R.layout.fragment_modify_excersice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chipMucDichTap = view.findViewById(R.id.chipMucdichtap);
        chipForcusMucle = view.findViewById(R.id.chipMucles);
        chipTypeOfExcercise = view.findViewById(R.id.chipTypeOfExcercise);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnDelete = view.findViewById(R.id.btnDelete);
        imgExercise = view.findViewById(R.id.imgExercise);
        spinnerLevel = view.findViewById(R.id.spinnerLevel);
        txtEquipment = view.findViewById(R.id.txtEquipment);
        txtCaloriesPerRep = view.findViewById(R.id.txtCalories);
        txtVideoUrl = view.findViewById(R.id.txtVideoUrl);
        txtName = view.findViewById(R.id.txtName);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        //Load data
        Glide.with(view).load(currentExercise.getImgUrl()).into(imgExercise);
        Util.loadChip(chipMucDichTap, ListMucDichTap.getAll(), view.getContext(), Util.reverseStringToList(currentExercise.getMucDichTap(), ","));
        Util.loadChip(chipForcusMucle, ListMuscleFocus.getAll(), view.getContext(), Util.reverseStringToList(currentExercise.getMuscleFocus(), ","));
        Util.loadChip(chipTypeOfExcercise, ListTypeOfExcercise.getAll(), view.getContext(), Util.reverseStringToList(currentExercise.getTypeOfExercise(), ","));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, ListLevel.getAll());
        spinnerLevel.setAdapter(spinnerAdapter);
        spinnerLevel.setSelection(spinnerAdapter.getPosition(currentExercise.getLevel()));
        txtName.setText(currentExercise.getName());
        txtCaloriesPerRep.setText(String.valueOf(currentExercise.getCaloriesPerRep()));
        txtVideoUrl.setText("Choose another video?");
        txtVideoUrl.setText("Choose another video?");
        txtEquipment.setText(currentExercise.getEquipment());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ExerciseDAO().delete(currentExercise.getId());
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentExercise.setName(txtName.getText().toString());
                currentExercise.setMuscleFocus(Util.conectListString(Util.selectedChips(chipForcusMucle), ","));
                currentExercise.setMucDichTap(Util.conectListString(Util.selectedChips(chipMucDichTap), ","));
                currentExercise.setEquipment(txtEquipment.getText().toString());
                currentExercise.setCaloriesPerRep(Float.valueOf(txtCaloriesPerRep.getText().toString()));
                currentExercise.setLevel(spinnerLevel.getSelectedItem().toString());
                currentExercise.setTypeOfExercise(Util.conectListString(Util.selectedChips(chipTypeOfExcercise), ","));
                new ExerciseDAO().update(currentExercise);
            }
        });

    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    private void chooseVideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            uploadPicture();
        } else if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            videoUri = data.getData();
            uploadVideo();
        }
    }

    private void uploadPicture() {
        final String randomKey = UUID.randomUUID().toString();
        final StorageReference imgUrl = storageReference.child("/Image/Exercise/" + randomKey + ".jpg");
        imgUrl.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        imgUrl.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                imgExercise.setImageURI(imageUri);
                                currentExercise.setImgUrl(uri.toString());
                            }
                        });
                        Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadVideo() {
        final String randomKey = UUID.randomUUID().toString();
        final StorageReference imgUrl = storageReference.child("Video/" + randomKey);

        imgUrl.putFile(videoUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        imgUrl.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                txtVideoUrl.setText("Click here to change video");
                                currentExercise.setVideoUrl(uri.toString());
                            }
                        });
                        Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}