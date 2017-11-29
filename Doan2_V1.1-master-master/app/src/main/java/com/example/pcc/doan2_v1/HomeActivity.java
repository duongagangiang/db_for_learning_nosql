package com.example.pcc.doan2_v1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcc.doan2_v1.adapter.MyAdapter;
import com.example.pcc.doan2_v1.model.Tintuc;
import com.example.pcc.doan2_v1.model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.toptas.fancyshowcase.FancyShowCaseView;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener{
    NavigationView navigationView;
    MyAdapter adapter;
    List<Tintuc> listTintuc=new ArrayList<>();
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    TextView txtUsername;
    ImageView btn_DangNhap;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    ImageView img_Avatar;
    LoginButton loginFacebook;
    CallbackManager callbackManager;
    String TAG="MainActivity";
    ImageView loginFB;

    int loaiDangNhap=0;//0: Chua dang nhap, 1: Dang nhap TK firebase, 2: Dang nhap TK facebook
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Trang chủ");
        setSupportActionBar(toolbar);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView_Dstt);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        getDanhsachTintuc();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        btn_DangNhap=header.findViewById(R.id.btnDangnhap);
        loginFB=(ImageView)header.findViewById(R.id.btnDangnhapFB);
        img_Avatar=header.findViewById(R.id.img_avatar);
        loginFacebook= header.findViewById(R.id.btnloginFaceBook);
        txtUsername=header.findViewById(R.id.txtUsername);

        loginFacebook.setVisibility(View.INVISIBLE);
        btn_DangNhap.setVisibility(View.VISIBLE);
        loginFB.setVisibility(View.VISIBLE);
        loginFB.setEnabled(true);
        btn_DangNhap.setEnabled(true);
        loginFacebook.setEnabled(true);
        enableLogout();
        hideItem();
        btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
        else{
            FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
            if(firebaseUser!=null) {
                loaiDangNhap=1;
                visibleItem();
                unenableLogout();
                //region CAI DAT ẨN HIỆN BUTTON KHI ĐĂNG NHẬP THÀNH CÔNG
                loginFacebook.setVisibility(View.INVISIBLE);
                btn_DangNhap.setVisibility(View.INVISIBLE);
                loginFB.setVisibility(View.INVISIBLE);
                loginFB.setEnabled(false);
                btn_DangNhap.setEnabled(false);
                loginFacebook.setEnabled(false);
                //endregion
                String email = firebaseUser.getEmail();
                txtUsername.setText(email);
            }
        }
        // dang nhap facebook
        callbackManager=CallbackManager.Factory.create();
        loginFacebook.setReadPermissions("email", "public_profile");
        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();
        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if (user !=null){
                    unenableLogout();
                    //region CAI DAT ẨN HIỆN BUTTON KHI ĐĂNG NHẬP THÀNH CÔNG
                    loginFacebook.setVisibility(View.INVISIBLE);
                    btn_DangNhap.setVisibility(View.INVISIBLE);
                    loginFB.setVisibility(View.INVISIBLE);
                    loginFB.setEnabled(false);
                    btn_DangNhap.setEnabled(false);
                    loginFacebook.setEnabled(false);
                    //endregion
                    String name= user.getDisplayName();
                    Uri uri= user.getPhotoUrl();
                    if(uri!=null){
                        Picasso.with(HomeActivity.this).load(uri).into(img_Avatar);
                        txtUsername.setText(name);
                    }
                    loginFB.setEnabled(false);
                    btn_DangNhap.setEnabled(false);

                }else {
                    Log.d(TAG,"onAuthStateChanged:signed_out");

                }
            }
        };

    }

    //region Dang nhap faceBook bằng modify button
    public void loginFB(View v){
        if(v.getId()==R.id.btnDangnhapFB)
        {
            loginFacebook.performClick();
        }
    }
    //endregion
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener !=null){
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void handleFacebookAccessToken(AccessToken accessToken){
        Log.d(TAG, "handleFacebookAccessToken:" + accessToken);
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithCredential:success"+task.isSuccessful());
                writeUser();
                if(!task.isSuccessful()){
                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    Toast.makeText(HomeActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    hideItem();
                    loaiDangNhap=2;
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
    private void writeUser()
    {
        FirebaseUser currenUser= firebaseAuth.getCurrentUser();
        DatabaseReference mdatabase= FirebaseDatabase.getInstance().getReference();
        String password= "";
        String gmail= currenUser.getEmail();
        User user= new User(gmail,password);
        mdatabase.child("users").child(currenUser.getUid()).setValue(user);
    }

    //

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Tin tức cần tìm");
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_danhmuctintuc) {
            Toast.makeText(getApplicationContext(),"Hiển thị danh mục tin tức hiện có",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_dangxuat) {
            hideItem();
            loaiDangNhap=0;
            firebaseAuth.signOut();
            enableLogout();
            txtUsername.setText("");
            //region CAI DAT BUTTON AN HIEN KHI DANG XUAT
            btn_DangNhap.setVisibility(View.VISIBLE);
            loginFB.setVisibility(View.VISIBLE);
            loginFacebook.setVisibility(View.INVISIBLE);
            loginFB.setEnabled(true);
            btn_DangNhap.setEnabled(true);
            loginFacebook.setEnabled(true);
            //endregion
            // đăng xuất facebook
            LoginManager.getInstance().logOut();
            FirebaseAuth.getInstance().signOut();
            txtUsername.setText("Chọn hình thức đăng nhập");
            img_Avatar.setImageResource(R.drawable.ic_user);
            Toast.makeText(getApplicationContext(),"Đã Đăng xuất tài khoản",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_thongtintaikhoan) {
            Toast.makeText(getApplicationContext(),"Hiển thị thông tin tài khoản của người dùng",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_quanlychude) {
            startActivity(new Intent(getApplicationContext(),QuanlyChudeActivity.class));
        } else if (id == R.id.nav_quanlytintuc) {
            startActivity(new Intent(getApplicationContext(),QuanlyTinActivity.class));
        } else if (id == R.id.nav_quanlyuser){
            startActivity(new Intent(getApplicationContext(),QuanlyUserActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void getDanhsachTintuc(){
        databaseReference= FirebaseDatabase.getInstance().getReference("Tintuc");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listTintuc.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Tintuc tintuc=snapshot.getValue(Tintuc.class);
                    if(tintuc.getActive()==1)
                    listTintuc.add(tintuc);
                }
                adapter=new MyAdapter(getApplicationContext(),listTintuc,txtUsername.getText().toString());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),""+databaseError.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    //region Tim kiem tin tuc khi nhap thong tin vao o tim kiem
    @Override
    public boolean onQueryTextChange(String newText) {
        newText=newText.toLowerCase();
        ArrayList<Tintuc> newarrayList=new ArrayList<>();
        for(Tintuc tintuc:listTintuc){
            String tieude=tintuc.getTitle().toLowerCase();
            if(tieude.contains(newText))
                newarrayList.add(tintuc);
        }
        adapter.setFilter(newarrayList);
        return false;
    }
    //endregion
    //Khong cho dang xuat khi chua dang nhap
    private void enableLogout(){
        Menu nav_menu=navigationView.getMenu();
        nav_menu.findItem(R.id.nav_dangxuat).setEnabled(false);
    }
    private void unenableLogout(){
        Menu nav_menu=navigationView.getMenu();
        nav_menu.findItem(R.id.nav_dangxuat).setEnabled(true);
    }
    //Phan Quyen Truy Cap
    private void hideItem(){
        Menu nav_menu=navigationView.getMenu();
        nav_menu.findItem(R.id.nav_quanlychude).setVisible(false);
        nav_menu.findItem(R.id.nav_quanlytintuc).setVisible(false);
        nav_menu.findItem(R.id.nav_quanlyuser).setVisible(false);
    }
    private void visibleItem(){
        Menu nav_menu=navigationView.getMenu();
        nav_menu.findItem(R.id.nav_quanlychude).setVisible(true);
        nav_menu.findItem(R.id.nav_quanlytintuc).setVisible(true);
        nav_menu.findItem(R.id.nav_quanlyuser).setVisible(true);
    }
}
