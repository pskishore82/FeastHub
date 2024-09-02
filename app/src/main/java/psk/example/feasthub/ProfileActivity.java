// ProfileFragment.java

package psk.example.feasthub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;


import com.example.feasthub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends Fragment {

    private ImageView profilepic;
    private TextView profilename, gmail;
    private CardView logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profile, container, false);

        profilepic = view.findViewById(R.id.profile_image);
        profilename = view.findViewById(R.id.profile_name);
        gmail = view.findViewById(R.id.profile_email);
        logout = view.findViewById(R.id.profile_logout);
        // Retrieve user profile information from SharedPreferences
        SharedPreferences preferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String name = preferences.getString("user_display_name", "DefaultName");
        String profileImageUriString = preferences.getString("user_photo_uri", null);
        String mail = preferences.getString("user_email", "DefaultMail");

        // Set the user profile information in the views
        profilename.setText(name);
        gmail.setText(mail);

        // Load profile image using Picasso if available
        if (profileImageUriString != null) {
            Uri userProfileImageUri = Uri.parse(profileImageUriString);
            Picasso.get().load(userProfileImageUri).into(profilepic);
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        return view;
    }

    public void logout() {
        // Clear user session data
        SharedPreferences preferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        // Navigate to the login screen or another appropriate destination
        Intent intent = new Intent(requireActivity(), Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear back stack
        startActivity(intent);
        requireActivity().finish(); // Finish the current activity
    }

}
