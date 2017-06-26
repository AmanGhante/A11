package com.example.aman.a11;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Menu4 extends Fragment implements MantraAdapter.onClickListner{


    private SwipeRefreshLayout mySwipeRefreshLayout;

    private List<Mantra> mantraList= new ArrayList<>();
    private RecyclerView recycleView4;
    private MantraAdapter adapter;
    private String LOG_TAG = "Swipe Up";
    Toast mToast;

    String GET_JSON_DATA_HTTP_URL = "http://amanghante.in/mantra.php";
    String JSON_ID = "id";
    String JSON_TITLE = "title";
    String JSON_DES = "des";

    JsonArrayRequest jsonArrayRequest;

    RequestQueue requestQueue;

    private TextView errorMesssageTextView;

    private Button mReloadButton;

    private ProgressBar mLoadingIndicator;

    Mantra mantra;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Menu4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Menu4.
     */
    // TODO: Rename and change types and number of parameters
    public static Menu4 newInstance(String param1, String param2) {
        Menu4 fragment = new Menu4();
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
        View view= inflater.inflate(R.layout.fragment_menu4, container, false);

        errorMesssageTextView = (TextView)view.findViewById(R.id.errorMessage4);
        mLoadingIndicator = (ProgressBar)view. findViewById(R.id.pb_loading_indicator4);
        mReloadButton = (Button)view.findViewById(R.id.refreshButton4);

        mReloadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                Run();
            }
        });


          /*
        ** Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
         * performs a swipe-to-refresh gesture.

        mySwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh4);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        JSON_DATA_WEB_CALL();
                    }
                }
        );*/

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) view.findViewById(R.id.adView4);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        recycleView4=(RecyclerView) view.findViewById(R.id.recycleView4);
        adapter =new MantraAdapter(mantraList, this);
        RecyclerView.LayoutManager gLayoutManager=new LinearLayoutManager(this.getActivity());
        recycleView4.setLayoutManager(gLayoutManager);
        recycleView4.setItemAnimator(new DefaultItemAnimator());
        //recycleView4.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        //recycleView4.setAdapter(adapter);

        Run();


        return view;
    }


    private void Run (){
        ConnectivityManager connectivityManager =(ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()) {
            showLoading();
            JSON_DATA_WEB_CALL();
        }else
        {
            showErrorMessage();
        }
    }



    private void showErrorMessage(){
        errorMesssageTextView.setVisibility(View.VISIBLE);
        mReloadButton.setVisibility(View.VISIBLE);
        recycleView4.setVisibility(View.INVISIBLE);
    }

    private void showDataView() {
        /* First, hide the loading indicator */
        errorMesssageTextView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        /* Finally, make sure the weather data is visible */
        recycleView4.setVisibility(View.VISIBLE);

        mReloadButton.setVisibility(View.INVISIBLE);
    }

    private void showLoading() {
        /* Then, hide the weather data */
        recycleView4.setVisibility(View.INVISIBLE);
        /* Finally, show the loading indicator */
        mLoadingIndicator.setVisibility(View.VISIBLE);

        mReloadButton.setVisibility(View.INVISIBLE);

        errorMesssageTextView.setVisibility(View.INVISIBLE);
    }


    /**********************************************************************************************************/

    public void JSON_DATA_WEB_CALL(){

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        //progressBar.setVisibility(View.GONE);
                        showDataView();
                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            Mantra mantra = new Mantra();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                //mantra.setA1(json.getString(JSON_ID));

                mantra.setA2(json.getString(JSON_TITLE));

                mantra.setA3(json.getString(JSON_DES));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            mantraList.add(mantra);
        }

        adapter = new MantraAdapter(mantraList,this);

        recycleView4.setAdapter(adapter);

        //mySwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClickListner(int pos) {

        if (mToast!=null){
            mToast.cancel();
        }


        Intent intent;
        intent = new Intent(getActivity(), Dispay.class);
        mantra = mantraList.get(pos);
        intent.putExtra(Intent.EXTRA_TEXT, mantra.getA3());
        startActivity(intent);

        /*switch (pos){
            case 0:
                intent = new Intent(getActivity(), Dispay.class);
                intent.putExtra(Intent.EXTRA_TEXT,"d1");
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getActivity(),Dispay.class);
                intent.putExtra(Intent.EXTRA_TEXT,"d2");
                startActivity(intent);
                break;
        }*/
    }

    /*
    public List<Mantra> fill_with_data() {

        mantraList.add(new Mantra("Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", "bbva"));
        mantraList.add(new Mantra("Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", "bbva"));
        mantraList.add(new Mantra("Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", "bbva"));
        mantraList.add(new Mantra("Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", "bbva"));
        mantraList.add(new Mantra("Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", "bbva"));

        return mantraList;
    }
     */
}
