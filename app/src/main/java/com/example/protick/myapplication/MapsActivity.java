package com.example.protick.myapplication;



        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.graphics.Color;
        import android.os.AsyncTask;
        import android.support.v4.app.FragmentActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import com.android.volley.DefaultRetryPolicy;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.android.volley.toolbox.Volley;
        import com.google.android.gms.maps.CameraUpdate;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.MapFragment;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.android.gms.maps.model.Polyline;
        import com.google.android.gms.maps.model.PolylineOptions;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.*;

        import static android.R.id.list;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static GoogleMap mMap;
    public static int k;
    public static int all=25;
    public static ArrayList<String>Bus_Stops;
    private Button btnSubmit,btnSubmit1;

    // public static ArrayList<ArrayList<Integer>>trace;
    public static ArrayList<Double>Longs,Lats;
    private MarkerOptions options = new MarkerOptions();
    private ArrayList<LatLng> latlngs = new ArrayList<>();
    public static ArrayList<LatLng> latlng = new ArrayList<>();
    JSONArray array;
    static final LatLng DUBLIN = new LatLng(23.757273, 90.390736);
    ProgressDialog pDialog;
    public static ArrayList<ArrayList<Double>> listdistance = new ArrayList<ArrayList<Double>>(100);
    public static HashMap<Integer,Double>dps;
    public static HashMap<Integer,ArrayList<Integer>> trace = new HashMap<>();
    public  static ArrayList<ArrayList<Integer> > arrayLists=new ArrayList<ArrayList<Integer>>();
    public static int i,pos,c=0,f=0,r1=0;
    public static HashMap<Integer,Double>edgemap,time,insta;
    public static HashMap<Integer,Integer>will;
    RequestQueue requestQueue,requestQueue1;
    public static String jsonstring;
    public static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>(100);
    public static ArrayList<Integer>vals;
    public ArrayList<Integer> vel=new ArrayList<>();
    public ArrayList<Integer> pkm=new ArrayList<>();
    static final LatLng DHAKA = new LatLng(23.8103,
            90.4125);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        edgemap = new HashMap<>();
        time = new HashMap<>();
        will = new HashMap<>();
        insta = new HashMap<>();
        dps = new HashMap<>();
        btnSubmit = (Button) findViewById(R.id.button3);
        btnSubmit1 = (Button) findViewById(R.id.button4);
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                    Intent in = new Intent(MapsActivity.this,FromToPage.class);
                    startActivity(in);

                }
            

        });


        btnSubmit1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent in = new Intent(MapsActivity.this,about.class);
                startActivity(in);

            }


        });

        r1 = 1;
        setup_nodes();
        setup_edges();
        setup_distance();


        ArrayList<Integer> a;
        a = new ArrayList<>();


        a.add(11);
        a.add(10);
        a.add(9);
        a.add(13);
        a.add(14);
        a.add(8);

        arrayLists.add(a);

        //System.out.println("finaleoo "+arrayLists.get(0).size());
        a = new ArrayList<>();


        a.add(13);
        a.add(5);
        a.add(7);
        a.add(8);
        a.add(14);
        a.add(13);

        arrayLists.add(a);
        //System.out.println("finaleoo "+arrayLists.get(1).size());

        a = new ArrayList<>();
        a.add(11);
        a.add(12);
        a.add(1);
        a.add(15);
        a.add(6);
        a.add(7);
        a.add(8);
        arrayLists.add(a);
        a = new ArrayList<>();
        a.add(11);
        a.add(10);
        a.add(9);
        a.add(2);
        a.add(3);
        a.add(5);
        a.add(4);
        a.add(6);
        a.add(7);
        a.add(8);
        arrayLists.add(a);
        a = new ArrayList<>();
        a.add(9);
        a.add(2);
        a.add(3);
        a.add(5);
        a.add(13);
        a.add(9);
        arrayLists.add(a);
        a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(5);
        a.add(7);
        a.add(8);
        arrayLists.add(a);
        a = new ArrayList<>();
        a.add(3);
        a.add(5);
        a.add(4);
        a.add(6);
        a.add(7);
        a.add(8);
        a.add(14);
        a.add(13);
        a.add(9);
        a.add(2);
        a.add(3);
        arrayLists.add(a);
        a = new ArrayList<>();
        a.add(12);
        a.add(1);
        a.add(2);
        a.add(9);
        a.add(10);
        a.add(11);
        a.add(12);
        arrayLists.add(a);
        //a=new ArrayList<>();


        pkm.add(-1);
        pkm.add(10);
        pkm.add(8);
        pkm.add(20);
        pkm.add(12);
        pkm.add(8);
        pkm.add(10);
        pkm.add(11);
        pkm.add(5);

        vel.add(-1);
        vel.add(6);
        vel.add(7);
        vel.add(3);
        vel.add(4);
        vel.add(6);
        vel.add(5);
        vel.add(5);
        vel.add(8);




        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(  GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(Longs.get(2), Lats.get(2));
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.animateCamera(CameraUpdateFactory.zoomTo( 20.0f ));
        createMarker();
        k=1;
        for (LatLng point : latlngs) {
            options.position(point);

            options.title(Bus_Stops.get(k));
            options.snippet("Bus Stand");
            googleMap.addMarker(options);
            k++;
        }

       /* new Thread(new Runnable() {
            @Override
            public void run() {

                while (f<19) {

                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        k=0;

                        int len=list.size();
                        for (i = 1; i < len; i++) {
                            vals = list.get(i);
                            r1++;
                            int lenvals = vals.size();
                            //doubles = new ArrayList<>();

                            for (int j = 0; j < lenvals; j++) {
                                pos = vals.get(j);
                                c=0;
                                if(pos>i)
                                {   //System.out.println("haghu");
                                    //new GetDirection().execute(i,pos,k);
                                    k++;

                                }
                            }
                        }
                    }
                });
            }
        }).start();*/


        /*Polyline line1 = mMap.addPolyline(new PolylineOptions()
                .add(latlngs.get(0), latlngs.get(1))
                .width(5)
                .color(0xFFFF0000));
        Polyline line2 = mMap.addPolyline(new PolylineOptions()
                .add(latlngs.get(1), latlngs.get(2))
                .width(5)
                .color(0xFFFF0000));*/
    }

    void highlight()
    {

    }
    void setup_nodes()
    {
       /*Add as global
        */

        Bus_Stops=new ArrayList<>();
        Bus_Stops.add("none");
        Bus_Stops.add("Gabtoli");
        Bus_Stops.add("Shymoli");
        Bus_Stops.add("College Gate");
        Bus_Stops.add("Mohammadpur");
        Bus_Stops.add("Asad Gate");
        Bus_Stops.add("Jigatola");
        Bus_Stops.add("Science Lab");
        Bus_Stops.add("Azimpur");
        Bus_Stops.add("Taltola");
        Bus_Stops.add("Kazipara");
        Bus_Stops.add("Mirpur-10");
        Bus_Stops.add("Mirpur-1");
        Bus_Stops.add("Farmgate");
        //23.757303, 90.389933
        Bus_Stops.add("Shahbag");
        //23.739195, 90.395910
        Bus_Stops.add("Hazaribag");
        //23.736215, 90.362946

        Longs=new ArrayList<>();
        Longs.add(0.0);
        Longs.add(23.7830528);
        Longs.add(23.774869);
        Longs.add(23.767953);
        Longs.add(23.757309);
        Longs.add(23.760263);
        Longs.add(23.738503);
        Longs.add(23.738932);
        Longs.add(23.726956);
        Longs.add(23.783460);
        Longs.add(23.797105);
        Longs.add(23.806703);
        Longs.add(23.798359);
        Longs.add(23.757303);
        Longs.add(23.739195);
        Longs.add(23.736215);

        Lats=new ArrayList<>();
        Lats.add(0.0);
        Lats.add(90.3420748);
        Lats.add(90.365255);
        Lats.add(90.369249);
        Lats.add(90.361859);
        Lats.add(90.372698);
        Lats.add(90.376060);
        Lats.add(90.383673);
        Lats.add(90.386184);
        Lats.add(90.378501);
        Lats.add(90.372804);
        Lats.add(90.368565);
        Lats.add(90.353398);
        Lats.add(90.389933);
        Lats.add(90.395910);
        Lats.add(90.362946);
    }

    void setup_edges()
    {   /*Add as global
        */

        vals=new ArrayList<>();
        vals.add(2);
        vals.add(12);

        list.add(vals);
        vals=new ArrayList<>();
        vals.add(2);
        vals.add(12);
        vals.add(15);
        list.add(vals);
        vals=new ArrayList<>();
        vals.add(1);
        vals.add(3);
        vals.add(9);
        list.add(vals);
        vals=new ArrayList<>();
        vals.add(2);
        vals.add(5);
        list.add(vals);
        vals=new ArrayList<>();
        vals.add(5);
        vals.add(6);
        list.add(vals);
        vals=new ArrayList<>();
        vals.add(3);
        vals.add(4);

        vals.add(7);
        vals.add(13);
        list.add(vals);
        vals=new ArrayList<>();
        vals.add(4);

        vals.add(7);
        vals.add(15);
        list.add(vals);
        vals=new ArrayList<>();
        vals.add(5);
        vals.add(8);
        list.add(vals);
        vals=new ArrayList<>();
        vals.add(7);
        vals.add(14);

        list.add(vals);
        vals=new ArrayList<>();
        vals.add(2);
        vals.add(10);
        vals.add(13);
        list.add(vals);
        vals=new ArrayList<>();
        vals.add(9);
        vals.add(11);
        list.add(vals);
        vals=new ArrayList<>();
        vals.add(10);
        vals.add(12);
        list.add(vals);
        vals=new ArrayList<>();
        vals.add(1);
        vals.add(11);
        list.add(vals);
        vals=new ArrayList<>();
        vals.add(5);
        vals.add(9);
        vals.add(14);
        list.add(vals);

        vals= new ArrayList<>();
        vals.add(8);
        vals.add(13);
        list.add(vals);

        vals=new ArrayList<>();
        vals.add(1);
        vals.add(6);
        list.add(vals);



    }

    void setup_distance() {   /*Add as global
        */
        //Add to on create
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        //Add to gradle
        //compile 'com.mcxiaoke.volley:library:1.0.18'
        int len = list.size();
        //"http://maps.googleapis.com/maps/api/distancematrix/json?origins=23.774869,90.365255&destinations=23.7830528,90.3420748&mode=driving&sensor=false&key=AIzaSyAUm2jQYITyxzxQdADNqZoBWXCoStdxzs4";

        for (i = 1; i < len; i++) {
            vals = list.get(i);
            int lenvals = vals.size();
            //doubles = new ArrayList<>();

            for (int j = 0; j < lenvals; j++) {
                pos = vals.get(j);

                if(pos>i) {
                    //System.out.println("hg");
                    //jsonstring = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + String.valueOf(Longs.get(pos)) + "," + String.valueOf(Lats.get(pos)) + "&destinations=" + String.valueOf(Longs.get(i)) + "," + String.valueOf(Lats.get(i)) + "&mode=driving&sensor=false";
                    //new GetDistance().execute(i, pos, 1);

                }

            }

        }
        //new GetDirection().execute(11,8,1);
    }
    public void createMarker()
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DHAKA, 15));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12), 2000, null);
        //latlngs.add(new LatLng(23.757273, 90.390736));
        //latlngs.add(new LatLng(23.806872, 90.368566));
        //latlngs.add(new LatLng(23.729004, 90.383417));
        for(int i=1;i<=15;i++){
            latlngs.add(new LatLng(Longs.get(i),Lats.get(i)));
        }
    }
    class GetDirection extends AsyncTask<Integer,Integer,Integer> {
        public  Integer srcd = 0, destd = 0,totalSize=0;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            requestQueue1 = Volley.newRequestQueue(getApplicationContext());
            /*pDialog = new ProgressDialog(MapsActivity.this);
            pDialog.setMessage("Loading route. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }

        protected Integer doInBackground(Integer... args) {

            int count = args.length;



            for (int i = 0; i < count; i++) {

                if (i == 0)
                    srcd = args[i];
                else if (i == 1)
                    destd = args[i];
                else
                    totalSize=args[i];


            }

            //Intent i = getIntent();
            //String startLocation = i.getStringExtra("startLoc");
            //String endLocation = i.getStringExtra("endLoc");
            //System.out.println(startLocation+endLocation);
            //startLocation = startLocation.replace(" ", "+");
            //endLocation = endLocation.replace(" ", "+");;
            String stringUrl = "http://maps.googleapis.com/maps/api/directions/json?origin="+Longs.get(srcd) + "," +Lats.get(srcd) +"&destination="+Longs.get(destd)+ "," +Lats.get(destd)+"&mode=driving&sensor=false";
            //System.out.println(stringUrl);
            //String stringUrl="http://maps.googleapis.com/maps/api/directions/json?origin=gabtoli,+dhaka&destination=shymoli,+dhaka&sensor=false";
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL(stringUrl);
                HttpURLConnection httpconn = (HttpURLConnection) url
                        .openConnection();
                if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader input = new BufferedReader(
                            new InputStreamReader(httpconn.getInputStream()),
                            8192);
                    String strLine = null;

                    while ((strLine = input.readLine()) != null) {
                        response.append(strLine);
                    }
                    input.close();


                }
                String jsonOutput = response.toString();
                System.out.println(response);

                JSONObject jsonObject = new JSONObject(jsonOutput);

                // routesArray contains ALL routes
                JSONArray routesArray = jsonObject.getJSONArray("routes");
                // Grab the first route
                JSONObject route = routesArray.getJSONObject(0);

                JSONObject poly = route.getJSONObject("overview_polyline");
                String polyline = poly.getString("points");


                latlng = decodePoly(polyline);





            }
            catch (Exception e) {

            }
            int d=0;
            while(d<=1000000)
            {
                d++;
            }

            return 1;
        }
        protected void onPostExecute(Integer res){
            super.onPostExecute(1);
            System.out.println(latlng.size());
            for (int i = 0; i < latlng.size() - 1; i++) {
                LatLng src = latlng.get(i);
                LatLng dest = latlng.get(i + 1);
                //System.out.println("hgd");
                if(totalSize%4==0) {
                    Polyline line = mMap.addPolyline(new PolylineOptions()
                            .add(new LatLng(src.latitude, src.longitude),
                                    new LatLng(dest.latitude, dest.longitude))
                            .width(10).color(Color.BLACK).geodesic(true));
                    line.setVisible(true);
                }
                else if(totalSize%4==1) {
                    Polyline line = mMap.addPolyline(new PolylineOptions()
                            .add(new LatLng(src.latitude, src.longitude),
                                    new LatLng(dest.latitude, dest.longitude))
                            .width(10).color(Color.BLACK).geodesic(true));
                    line.setVisible(true);
                }
                else if(totalSize%4==2) {
                    Polyline line = mMap.addPolyline(new PolylineOptions()
                            .add(new LatLng(src.latitude, src.longitude),
                                    new LatLng(dest.latitude, dest.longitude))
                            .width(10).color(Color.BLACK).geodesic(true));
                    line.setVisible(true);
                }
                else if(totalSize%4==3) {
                    Polyline line = mMap.addPolyline(new PolylineOptions()
                            .add(new LatLng(src.latitude, src.longitude),
                                    new LatLng(dest.latitude, dest.longitude))
                            .width(10).color(Color.BLACK).geodesic(true));
                    line.setVisible(true);
                }
                //pDialog.dismiss();

            }







        }





    }

    class GetDistance extends AsyncTask<Integer,Integer,Integer> {
        public  Integer src = 0, dest = 0;
        @Override

        protected Integer doInBackground(Integer... args) {
            int count = args.length;
            long totalSize = 0;


            for (int i = 0; i < count; i++) {

                if (i == 0)
                    src = args[i];
                else if (i == 1)
                    dest = args[i];


            }
            //System.out.println(src+" "+dest);
            jsonstring = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + String.valueOf(Longs.get(src)) + "," + String.valueOf(Lats.get(src)) + "&destinations=" + String.valueOf(Longs.get(dest)) + "," + String.valueOf(Lats.get(dest)) + "&sensor=false";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET,
                    jsonstring, null,new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //System.out.println(response.toString());
                    try {

                        f++;
                        JSONObject jsonRespRouteDistance = response
                                .getJSONArray("rows")
                                .getJSONObject(0)
                                .getJSONArray("elements")
                                .getJSONObject(0)
                                .getJSONObject("distance");

                        String distance = jsonRespRouteDistance.get("text").toString();
                        String delims = "[ ]+";
                        String[] tokens = distance.split(delims);
                        //System.out.println(Bus_Stops.get(src) + " " + Bus_Stops.get(dest) + " " + Double.parseDouble(tokens[0]));
                        Double d = Double.parseDouble(tokens[0]);
                        //edgemap.put(Math.min(src,dest)*10+Math.max(src,dest),d);
                        edgemap.put(src*100+dest,d);
                        edgemap.put(dest*100+src,d);
                        r1++;


                        //doubles.add(d);


                        //System.out.println(distance);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //System.out.append(error.getMessage());

                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    2000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            requestQueue.add(jsonObjectRequest);

            return null;


        }
    }



    /* Method to decode polyline points */
    private ArrayList<LatLng> decodePoly(String encoded) {

        ArrayList<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                //System.out.println("hgd");
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                // System.out.println("hgf");
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            //System.out.println("HSDFIHIASUHDI");
            poly.add(p);
        }

        return poly;
    }


}
