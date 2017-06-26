package com.example.aman.a11;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


public class Menu3 extends Fragment implements AartiAdapter.onClickListner {


    ///private SwipeRefreshLayout mySwipeRefreshLayout;

    private List<Aarti> aartiList= new ArrayList<>();
    private RecyclerView recycleView3;
    private AartiAdapter adapter;
    private String LOG_TAG = "Swipe Up";
    Toast mToast;

    String GET_JSON_DATA_HTTP_URL = "http://amanghante.in/aarti.php";
    String JSON_ID = "id";
    String JSON_TITLE = "title";
    String JSON_DES = "des";

    JsonArrayRequest jsonArrayRequest;

    RequestQueue requestQueue;

    private TextView errorMesssageTextView;

    private Button mReloadButton;

    private ProgressBar mLoadingIndicator;

    Aarti aarti;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public Menu3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Menu3.
     */
    // TODO: Rename and change types and number of parameters
    public static Menu3 newInstance(String param1, String param2) {
        Menu3 fragment = new Menu3();
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
        View view= inflater.inflate(R.layout.fragment_menu3, container, false);

        errorMesssageTextView = (TextView)view.findViewById(R.id.errorMessage3);
        mLoadingIndicator = (ProgressBar)view. findViewById(R.id.pb_loading_indicator3);
        mReloadButton = (Button)view.findViewById(R.id.refreshButton3);

        mReloadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                Run();
            }
        });


          /*
        ** Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
         * performs a swipe-to-refresh gesture.

        mySwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh3);
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
        AdView adView = (AdView) view.findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        recycleView3=(RecyclerView) view.findViewById(R.id.recycleView3);
        adapter =new AartiAdapter(aartiList, this);
        RecyclerView.LayoutManager gLayoutManager=new LinearLayoutManager(this.getActivity());
        recycleView3.setLayoutManager(gLayoutManager);
        recycleView3.setItemAnimator(new DefaultItemAnimator());
        //recycleView3.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        //recycleView3.setAdapter(adapter);


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
        recycleView3.setVisibility(View.INVISIBLE);
    }

    private void showDataView() {
        /* First, hide the loading indicator */
        errorMesssageTextView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        /* Finally, make sure the weather data is visible */
        recycleView3.setVisibility(View.VISIBLE);

        mReloadButton.setVisibility(View.INVISIBLE);
    }

    private void showLoading() {
        /* Then, hide the weather data */
        recycleView3.setVisibility(View.INVISIBLE);
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

            Aarti aarti = new Aarti();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                //aarti.setD1(json.getString(JSON_ID));

                aarti.setA2(json.getString(JSON_TITLE));

                aarti.setA3(json.getString(JSON_DES));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            aartiList.add(aarti);
        }

        adapter = new AartiAdapter(aartiList,this);

        recycleView3.setAdapter(adapter);

       // mySwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClickListner(int pos) {
        if (mToast!=null){
            mToast.cancel();
        }
        Intent intent;
        intent = new Intent(getActivity(), Dispay.class);
        aarti = aartiList.get(pos);
        intent.putExtra(Intent.EXTRA_TEXT, aarti.getA3());
        startActivity(intent);

    }

    /*
     public List<Aarti> fill_with_data() {

        //List<Ganesh> GaneshAarti = new ArrayList<>();

        aartiList.add(new Aarti("Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", "bbva"));
        aartiList.add(new Aarti("X-Men: Apocalypse is an upcoming American superhero film based on the X-Men characters that appear in Marvel Comics ", "goal"));
        aartiList.add(new Aarti("A feud between Captain America and Iron Man leaves the Avengers in turmoil.  ", "ronaldo"));
        aartiList.add(new Aarti("After reuniting with his long-lost father, Po  must train a village of pandas", "goal"));
        aartiList.add(new Aarti("Fleeing their dying home to colonize another, fearsome orc warriors invade the peaceful realm of Azeroth. ", "messs"));
        aartiList.add(new Aarti("Alice in Wonderland: Through the Looking Glass ", "ajfh"));
        aartiList.add(new Aarti("Alice in Wonderland: Through the Looking Glass ", "ajfh"));

        return aartiList;
    }
     */
}
