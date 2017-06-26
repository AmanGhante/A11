package com.example.aman.a11;

import android.support.v4.app.Fragment;


public class Menu6 extends Fragment{
 }
 /*implements OthersAdapter.onClickListner{

    private List<Others> othersList= new ArrayList<>();
    private RecyclerView recycleView6;
    private OthersAdapter oAdapter;
    Toast mToast;

    String GET_JSON_DATA_HTTP_URL = "http://amanghante.in/aarti.php";
    String JSON_ID = "id";
    String JSON_TITLE = "title";
    String JSON_DES = "des";

    JsonArrayRequest jsonArrayRequest;

    RequestQueue requestQueue;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Menu6() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Menu6.
     */
    // TODO: Rename and change types and number of parameters
  /*  public static Menu6 newInstance(String param1, String param2) {
        Menu6 fragment = new Menu6();
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
        JSON_DATA_WEB_CALL();
        //fill_with_data();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_menu6, container, false);

          /*
        ** Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
         * performs a swipe-to-refresh gesture.



        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) view.findViewById(R.id.adView6);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        recycleView6=(RecyclerView) view.findViewById(R.id.recycleView6);
        oAdapter = new OthersAdapter(othersList,this);
        RecyclerView.LayoutManager gLayoutManager=new LinearLayoutManager(this.getActivity());
        recycleView6.setLayoutManager(gLayoutManager);
        recycleView6.setItemAnimator(new DefaultItemAnimator());
        //recycleView6.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        recycleView6.setAdapter(oAdapter);



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
        recycleView2.setVisibility(View.INVISIBLE);
    }

   private void showDataView() {
        /* First, hide the loading indicator *//*
        errorMesssageTextView.setVisibility(View.INVISIBLE);
                mLoadingIndicator.setVisibility(View.INVISIBLE);
        /* Finally, make sure the weather data is visible *//*
                recycleView2.setVisibility(View.VISIBLE);

                mReloadButton.setVisibility(View.INVISIBLE);
                }

private void showLoading() {
        /* Then, hide the weather data *//*
        recycleView2.setVisibility(View.INVISIBLE);
        /* Finally, show the loading indicator *//*
        mLoadingIndicator.setVisibility(View.VISIBLE);

        mReloadButton.setVisibility(View.INVISIBLE);

        errorMesssageTextView.setVisibility(View.INVISIBLE);
        }


public void JSON_DATA_WEB_CALL(){

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        //progressBar.setVisibility(View.GONE);

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

            Devi devi = new Devi();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                //devi.setD1(json.getString(JSON_ID));

                devi.setD2(json.getString(JSON_TITLE));

                devi.setD3(json.getString(JSON_DES));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            DeviAarti.add(devi);
        }

        dAdapter = new DeviAdapter(DeviAarti,this);

        recycleView2.setAdapter(dAdapter);
    }

    @Override
    public void onItemClickListner(int pos) {
        if (mToast != null) {
            mToast.cancel();
        }
        String toast = "" + pos;

        Intent intent;
        switch (pos) {
            case 0:
                intent = new Intent(getActivity(), Dispay.class);
                intent.putExtra(Intent.EXTRA_TEXT, "o1");
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getActivity(), Dispay.class);
                intent.putExtra(Intent.EXTRA_TEXT, "o2");
                startActivity(intent);
                break;
        }
    }




    public List<Others> fill_with_data() {


        othersList.add(new Others("Hanuman Chalisa", "o1"));
        othersList.add(new Others("Hanuman Chalisa", "o2"));
        othersList.add(new Others("Hanuman Chalisa", "o3"));
        othersList.add(new Others("Hanuman Chalisa", "o4"));

        return othersList;
    }
}*/