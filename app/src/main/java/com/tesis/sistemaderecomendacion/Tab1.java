package com.tesis.sistemaderecomendacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Tab1 extends Fragment implements Notification.Listener,Notification {
    private ProgressBar spinner;
    private CallHttp callHttp;
    private TransparentProgressDialog pd;
    private View view;
    private RecyclerView recyclerView;
    private  Adapter adapter;
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        view = layoutInflater.inflate(R.layout.tap1, viewGroup, false);
        pd=  new TransparentProgressDialog(view.getContext(),R.drawable.loginicon);
        callHttp=new CallHttp(view.getContext());
        recyclerView = view.findViewById(R.id.rv_play_list);

        pd.show();
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        callHttp.execute("https://sistemaderecomendacion.herokuapp.com/grupo/getTestRecomendacion?id="+id);

        List<item> mlist = new ArrayList<>();

        /*for (int i = 0; i < MusicaService.getInstance().getCancionEscuchada().size(); i++){
            CancionInfo cancionInfo = MusicaService.getInstance().getCancionEscuchada().get(i);
            item itemC = new item(R.drawable.logo_music,
                    cancionInfo.Nombre, R.drawable.marron_five,
                    cancionInfo.ArtistName);

            mlist.add(itemC);
        }

        adapter = new Adapter(view.getContext(),mlist);*/
        //recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager( view.getContext()));


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //RecomendacionVm music = MusicaService.getInstance().getCancionInfos();
    }



    @Override
    public void register(ID id, Listener listener) {
        if(callHttp!=null){
            callHttp.SetListener(listener);
        }
    }

    @Override
    public void unregister(ID id, Listener listener) {
        if(callHttp!=null){
            callHttp.SetListener(null);
        }
    }

    @Override
    public <T> void post(ID id, T value) {

    }

    @Override
    public void onEvent(ID id, Object value) {
        if(id==ID.GetData){
            RecomendacionVm  resp=(RecomendacionVm) value;
            pd.dismiss();
            MusicaService.getInstance().setCancionInfos(resp);
            List<item> mlist = new ArrayList<>();

            for (int i = 0; i < MusicaService.getInstance().getCancionEscuchada().size(); i++){
                CancionInfo cancionInfo = MusicaService.getInstance().getCancionEscuchada().get(i);
                item itemC = new item(R.drawable.logo_music,
                        cancionInfo.Nombre, R.drawable.marron_five,
                        cancionInfo.ArtistName);

                mlist.add(itemC);
            }

            adapter = new Adapter(view.getContext(),mlist);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);


        }
    }

    @Override
    public void onResume() {
        super.onResume();

        register(ID.GetData,this);
        // register(ID.TOKEN,this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregister(ID.GetData,this);
    }
}
