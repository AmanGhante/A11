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


public class Menu1 extends Fragment implements GaneshAdapter.onClickListner {

    // private static final String URL ="http://http://localhost/newPhp.php";
    private static final int AARTI_LOADER_ID= 1;

    private TextView gEmptyStateTextView;

    private Ganesh Ganesh2;

    // A Native Express ad is placed in every nth position in the RecyclerView.
    public static final int ITEMS_PER_AD = 3;

    // The Native Express ad height.
    private static final int NATIVE_EXPRESS_AD_HEIGHT = 150;

    // The Native Express ad unit ID.
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1072772517";


    private SwipeRefreshLayout mySwipeRefreshLayout;

    private List<Ganesh> GaneshAarti = new ArrayList<>();
    private RecyclerView recycleView1;
    private GaneshAdapter gAdapter;
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


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Menu1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Menu1.
     */
    // TODO: Rename and change types and number of parameters
    public static Menu1 newInstance(String param1, String param2) {
        Menu1 fragment = new Menu1();
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

        View view = inflater.inflate(R.layout.fragment_menu1, container, false);

        errorMesssageTextView = (TextView)view.findViewById(R.id.errorMessage1);
        mLoadingIndicator = (ProgressBar)view. findViewById(R.id.pb_loading_indicator1);
        mReloadButton = (Button)view.findViewById(R.id.refreshButton1);

        mReloadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                Run();
            }
        });



        /*
        ** Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
         * performs a swipe-to-refresh gesture.

        mySwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        mySwipeRefreshLayout.setColorSchemeColors(//getResources().getColor(R.color.refresh_progress_1),
                //getResources().getColor(R.color.refresh_progress_2),
                getResources().getColor(R.color.refresh_progress_3));
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
        AdView adView = (AdView) view.findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);


        //GaneshAarti = new ArrayList<>();
        recycleView1 = (RecyclerView) view.findViewById(R.id.recycleView1);
        gAdapter = new GaneshAdapter(GaneshAarti, this);
        RecyclerView.LayoutManager gLayoutManager = new LinearLayoutManager(this.getActivity());
        recycleView1.setLayoutManager(gLayoutManager);
        recycleView1.setItemAnimator(new DefaultItemAnimator());
        //recycleView1.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        //recycleView1.setAdapter(gAdapter);

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
        recycleView1.setVisibility(View.INVISIBLE);
    }

    private void showDataView() {
        /* First, hide the loading indicator */
        errorMesssageTextView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        /* Finally, make sure the weather data is visible */
        recycleView1.setVisibility(View.VISIBLE);

        mReloadButton.setVisibility(View.INVISIBLE);
    }

    private void showLoading() {
        /* Then, hide the weather data */
        recycleView1.setVisibility(View.INVISIBLE);
        /* Finally, show the loading indicator */
        mLoadingIndicator.setVisibility(View.VISIBLE);

        mReloadButton.setVisibility(View.INVISIBLE);

        errorMesssageTextView.setVisibility(View.INVISIBLE);
    }







    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    /**************************************************Ganesh's Aarti's***************************************************************/


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

            Ganesh2 = new Ganesh();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                // GetDataAdapter2.setS1(json.getInt(JSON_ID));

                //Ganesh2.setG1(json.getString(JSON_ID));

                Ganesh2.setG2(json.getString(JSON_TITLE));

                Ganesh2.setG3(json.getString(JSON_DES));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            GaneshAarti.add(Ganesh2);
        }

        gAdapter = new GaneshAdapter(GaneshAarti,this);

        recycleView1.setAdapter(gAdapter);

       // mySwipeRefreshLayout.setRefreshing(false);
    }
/*

    public List<Ganesh> fill_with_data() {

        GaneshAarti.add(new Ganesh("Jai Ganesh Jai Ganesh",a1));
        GaneshAarti.add(new Ganesh("Jai ", a2));
        GaneshAarti.add(new Ganesh("A feud between Captain America and Iron Man leaves the Avengers in turmoil.  ", "ronaldo"));


        return GaneshAarti;
    }
*/
    @Override
    public void onItemClickListner(int pos) {
        if (mToast!=null){
            mToast.cancel();
        }
        String toast = ""+pos;


        Intent intent;
        intent = new Intent(getActivity(), Dispay.class);
        Ganesh2 = GaneshAarti.get(pos);
        intent.putExtra(Intent.EXTRA_TEXT, Ganesh2.getG3());
        startActivity(intent);
    }





/**************************************************************************************************************/
/*
String a1 = "Jai ganesh jai ganesh\n" +
        "Jai ganesh deva\n" +
        "Mata jaki parwati \n" +
        "Pita maha deva\n" +
        "\n" +
        "Jai ganesh jai ganesh\n" +
        "Jai ganesg deva\n" +
        "Mata jaki parwati \n" +
        "Pita maha deva\n" +
        "\n" +
        "Ek dant daya want \n" +
        "Char bhuuja dhari \n" +
        "\n" +
        "Ek dant daya want \n" +
        "Char bhuuja dhari \n" +
        "\n" +
        "Mathe sindor shoye\n" +
        "Muse ki sawari\n" +
        "Pan chadhe Phool Chadhe\n" +
        "Aur Chadhe Mewa \n" +
        "Laduan ko bhog lage\n" +
        "Sant kare seva\n" +
        "Jai ganesh deva...";

    String a2="Amana afjgfag fga; kjfgak;jff" +
            "dkjfksdjbfk;jg" +
            "ffjbkj;f" +
            "ffllddf;jh" +
            "afjabdjf" +
            "a ffkblafb" +
            "affbakfb" +
            "aadfjabfj" +
            "ljjdhg";
    */
}
