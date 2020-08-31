package com.tesis.sistemaderecomendacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class Tab2 extends Fragment {

    /*Observable integerObservable =  Observable.create(new Observable.OnSubscribe() {
        @Override
        public void call(Object o) {
            List<item> mlist = new ArrayList<>();

            for (int i = 0; i < MusicaService.getInstance().getCancionRecomendada().size(); i++){
                CancionInfo cancionInfo = MusicaService.getInstance().getCancionRecomendada().get(i);
                item itemC = new item(R.drawable.logo_music,
                        cancionInfo.Nombre, R.drawable.marron_five,
                        cancionInfo.ArtistName);

                mlist.add(itemC);
            }

            Adapter adapter = new Adapter(view.getContext(),mlist);
            recyclerView.setAdapter(adapter);
        }
    });*/
    private RecyclerView recyclerView;
    private View view;


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        view = layoutInflater.inflate(R.layout.tap2, viewGroup, false);
        //integerObservable.subscribe(MusicaService.getInstance().integerSubscriber2);

        MusicaService.getInstance().integerSubscriber2 = new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                List<item> mlist = new ArrayList<>();

                for (int i = 0; i < MusicaService.getInstance().getCancionRecomendada().size(); i++){
                    CancionInfo cancionInfo = MusicaService.getInstance().getCancionRecomendada().get(i);
                    item itemC = new item(R.drawable.logo_music,
                            cancionInfo.Nombre, R.drawable.marron_five,
                            cancionInfo.ArtistName);

                    mlist.add(itemC);
                }

                Adapter adapter = new Adapter(view.getContext(),mlist);
                recyclerView.setAdapter(adapter);

            }
        };

        recyclerView = view.findViewById(R.id.rv_play_list2);

        recyclerView.setLayoutManager(new LinearLayoutManager( view.getContext()));

        return view;
    }
}
