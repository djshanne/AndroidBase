package com.app.marvel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.app.marvel.base.BaseActivity;
import com.app.marvel.views.Controller;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.BindView;


/**
 * Created by Juan Delgado on 2/3/2017.
 */

public class HomeActivity extends BaseActivity implements Controller, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final int RC_SIGN_IN = 109;
    private HomeFragment homeFragment;
    private GoogleApiClient mGoogleApiClient;

    @BindView(R.id.mStatusTextView)
    public TextView mStatusTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        homeFragment = HomeFragment.getInstance(this);
        super.onCreate(savedInstanceState);

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Set the dimensions of the sign-in button.
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected View inflateMainView() {
        return LayoutInflater.from(this).inflate(R.layout.home_activity, null);
    }

    @Override
    protected int getMainFragmentId() {
        return homeFragment.getId();
    }

    @Override
    protected Fragment getMainFragment() {
        return homeFragment;
    }

    @Override
    protected int getMainIdContainer() {
        return R.id.home_main_container;
    }

    @Override
    protected String getMainFragmentTag() {
        return HomeFragment.class.getSimpleName();
    }

    @Override
    protected void afterMainViewIsInflated(Bundle savedInstanceState) {
    }


    @Override
    public HomeActivityPresenter getPresenter() {
        if (presenter == null) {
            presenter = new HomeActivityPresenter(this);
        }
        return (HomeActivityPresenter) presenter;
    }

    @Override
    protected int getLogoRsc() {
        return R.drawable.marvel_logo;
    }

    @Override
    protected int getLeftCornerIcon() {
        return 0;
    }

    @Override
    protected boolean leftIconShouldBeShownWhenDrawableZero() {
        return false;
    }

    @Override
    public void showProgressBar(boolean b) {
        super.showProgressBar(b);
    }

    @Override
    public void hideProgressBar(boolean b) {
        super.hideProgressBar(b);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            // ...
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    private void updateUI(boolean b) {
        mStatusTextView.setText(getString(R.string.signed_in_fmt, "NOOO"));
    }
}
