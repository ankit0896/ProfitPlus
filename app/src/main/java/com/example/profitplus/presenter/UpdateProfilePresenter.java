package com.example.profitplus.presenter;

import android.app.AlertDialog;
import android.content.Context;

import com.example.profitplus.R;
import com.example.profitplus.constant.RetrofitClient;
import com.example.profitplus.model.profileUpdateResponse.ProfileUModel;
import com.example.profitplus.model.profileUpdateResponse.ProfileUpdate;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfilePresenter {
    private Context context;
    UpdateProfilePre profilePre;
    private AlertDialog progressDialog;


    public UpdateProfilePresenter(Context context, UpdateProfilePre profilePre) {
        this.context = context;
        this.profilePre = profilePre;
        progressDialog = new SpotsDialog.Builder().setContext(context)
                .setTheme(R.style.loading)
                .setMessage(R.string.loading)
                .setCancelable(false)
                .build();
    }

    public interface UpdateProfilePre {
        void success(ProfileUModel profileUModel);

        void fail(String response);

        void error(String response);
    }

    public void updateProfile(String id, String token, String fName, String lName, String gender, String country,String email){
        progressDialog.show();
        Call<ProfileUpdate> updateCall = RetrofitClient.getInstance().getMyApi().updateProfile(token,id,fName,lName,gender,country,email);
        updateCall.enqueue(new Callback<ProfileUpdate>() {
            @Override
            public void onResponse(Call<ProfileUpdate> call, Response<ProfileUpdate> response) {
                if(response.isSuccessful()){
                    try {
                        ProfileUpdate update = response.body();
                        ProfileUModel profileUModel = update.getUser();
                        progressDialog.dismiss();
                        profilePre.success(profileUModel);
                    } catch (Exception e) {
                        progressDialog.dismiss();
                        profilePre.error(response.message());
                        e.printStackTrace();
                    }
                }else{
                    progressDialog.dismiss();
                    profilePre.error(response.message());
                }
            }

            @Override
            public void onFailure(Call<ProfileUpdate> call, Throwable t) {
                progressDialog.dismiss();
                profilePre.fail(t.getMessage());
            }
        });
    }
}
