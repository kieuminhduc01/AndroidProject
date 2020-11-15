package com.example.appexcerse.area.admin.chucNang.Excercise;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
 * Use the {@link FragmentAddExercise#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddExercise extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Exercise exercise = new Exercise();
    private ChipGroup chipMucDichTap;
    private ChipGroup chipForcusMucle;
    private ChipGroup chipTypeOfExcercise;
    private Button btnInsert;
    private Button btnUploadVideo;
    private Button btnUploadImage;
    private VideoView videoExercise;

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


    public FragmentAddExercise() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAddExercise.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAddExercise newInstance(String param1, String param2) {
        FragmentAddExercise fragment = new FragmentAddExercise();
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
        return inflater.inflate(R.layout.fragment_add_exercise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chipMucDichTap = view.findViewById(R.id.chipMucdichtap);
        chipForcusMucle = view.findViewById(R.id.chipMucles);
        chipTypeOfExcercise = view.findViewById(R.id.chipTypeOfExcercise);
        btnInsert = view.findViewById(R.id.btnInsert);
        imgExercise = view.findViewById(R.id.imgExercise);
        spinnerLevel = view.findViewById(R.id.spinnerLevel);
        txtEquipment = view.findViewById(R.id.txtEquipment);
        txtCaloriesPerRep = view.findViewById(R.id.txtCalories);

        btnUploadVideo = view.findViewById(R.id.btnUploadVideo);
        btnUploadImage = view.findViewById(R.id.btnUploadImage);
        videoExercise = view.findViewById(R.id.videoExercise);
        txtName = view.findViewById(R.id.txtName);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
//Load data
        Util.loadChip(chipMucDichTap, ListMucDichTap.getAll(), view.getContext() , null);
        Util.  loadChip(chipForcusMucle, ListMuscleFocus.getAll(), view.getContext(),null);
        Util. loadChip(chipTypeOfExcercise, ListTypeOfExcercise.getAll(), view.getContext(),null);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, ListLevel.getAll());
        spinnerLevel.setAdapter(spinnerAdapter);
        //Event
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    exercise.setName(txtName.getText().toString());
    exercise.setMuscleFocus(Util.conectListString(Util.selectedChips(chipForcusMucle),","));
    exercise.setMucDichTap(Util.conectListString(Util.selectedChips(chipMucDichTap),","));
    exercise.setEquipment(txtEquipment.getText().toString());
    exercise.setCaloriesPerRep(Float.valueOf(txtCaloriesPerRep.getText().toString()));
    exercise.setLevel(spinnerLevel.getSelectedItem().toString());
    exercise.setTypeOfExercise(Util.conectListString(Util.selectedChips(chipTypeOfExcercise),","));
    exercise.setId(UUID.randomUUID().toString());
    new ExerciseDAO().push(exercise);

            }
        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              choosePicture();
            }
        });

        btnUploadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               chooseVideo();
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
        final StorageReference imgUrl = storageReference.child("/Image/Exercise/" + randomKey+".jpg");
        imgUrl.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        imgUrl.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                imgExercise.setImageURI(imageUri);
                                exercise.setImgUrl(uri.toString());
                            }
                        });
                        Toast.makeText(getContext(),"Uploaded",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getContext(),"Failed",Toast.LENGTH_SHORT).show();
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

                               exercise.setVideoUrl(uri.toString());
                               playVideo();
                            }
                        });
                        Toast.makeText(getContext(),"Uploaded",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getContext(),"Failed",Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void playVideo() {
        videoExercise.setVideoURI(Uri.parse(exercise.getVideoUrl()));
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(videoExercise);
        videoExercise.setMediaController(mediaController);
        videoExercise.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoExercise.start();
            }
        });
    }



}