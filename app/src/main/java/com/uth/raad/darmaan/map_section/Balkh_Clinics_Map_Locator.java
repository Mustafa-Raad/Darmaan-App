package com.uth.raad.darmaan.map_section;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.uth.raad.darmaan.DarmaanDB;
import com.uth.raad.darmaan.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Balkh_Clinics_Map_Locator extends AppCompatActivity implements OnMapReadyCallback {

    //database
    DarmaanDB mapdb;
    GoogleMap mGoogleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clinics_map);
        //database
        //mapdb = new DarmaanDB(this);
        setTitle("کلینیک ها");


        // GOOGLE MAP METHODS
        if (googleServiceAvailable()) {
            //Toast.makeText(this, "Welcome to Doctors section", Toast.LENGTH_LONG).show();

            Toolbar mToolbar = (Toolbar) findViewById(R.id.mapToolbar);
            setSupportActionBar(mToolbar);
            initMap();
        } else {
            // No Google Maps Layout
        }// END OF GOOGLE MAP METHODS
    }


    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    public boolean googleServiceAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Cant connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        if(mGoogleMap != null){
            mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter(){

                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.info_pointer, null);
                    TextView tvLocality = (TextView) v.findViewById(R.id.tv_locality);
                    TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);
                    TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);
                    TextView tvSnippet = (TextView) v.findViewById(R.id.tv_snippet);


                    LatLng ll = marker.getPosition();
                    tvLocality.setText(marker.getTitle());
                    tvLat.setText("Latitude: "+ll.latitude);
                    tvLng.setText("Lontitude: "+ll.longitude);
                    tvSnippet.setText(marker.getSnippet());
                    return v;
                }
            });
        }

// mother error
        // self location finder code start
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//        }
//        mGoogleMap.setMyLocationEnabled(true);
        // self location finder code ends
    }

    // go to location without zoom start of code
    private void goToLocation(double v, double lat, double lng) {
        LatLng ll= new LatLng(lat,lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        mGoogleMap.moveCamera(update);
    }
    // go to location without zoom ends of code

    // go to location with zoom ... codes start
    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng ll= new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);
    }
    // go to location with zoom ... codes end

// start marking codes, search some where to have a marker there in map
    Marker marker;
    //search places codes to show
//    private void setMarker(String locality, double lat, double lng) {
//
//        if (marker != null){
//            marker.remove();
//        }
//
//        MarkerOptions options = new MarkerOptions()
//                                .title(locality)
//                                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
//                                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
//                                .position(new LatLng(lat, lng))
//                                .snippet("welome to this position");
//
//        marker = mGoogleMap.addMarker(options);
//
//    }

    // map option map_view_menu code starts from here
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
// map view modes items
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mapTypeNone:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;
            case R.id.mapTypeNormal:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.mapTypeTerrain:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.mapTypeSatellite:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.mapTypeHybrid:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


//ADDDRESSING STARTS
    // Static Address 1 codes
    public void Address1(View vv)  {

        double lat = 36.731342;
        double lng = 67.106385;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک تشخصیه التراسوند رفیق" +
                        "\n" +
                        "تداوی: امراض داخله" +
                        "\n" +
                        "تماس: 0773456506 ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    // static address 2 codes
    public void Address2(View vv)  {
        // Dr.TalebShah
        double lat = 36.730972;
        double lng = 67.104464;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک لیپکو" +
                        "\n" +
                        "تداوی: جذام و توبرکلوز" +
                        "\n" +
                        "تماس: 0778989512 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    // static address 3 codes
    public void Address3(View vv)  {
        // Dr.SayedZabihUllahKhadim
        double lat = 36.715817;
        double lng = 67.100286;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک التراسوند ابن سینا" +
                        "\n" +
                        "تداوی: امراض داخله" );
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    // static address 4 codes
    public void Address4(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.707553;
        double lng = 67.088306;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک صحی مردانه مزارشریف(پروگرام ملی کنترل ایدز)" +
                        "\n" +
                        "تداوی: رایگان(علاج معتادین زرقی)" +
                        "\n" +
                        "تماس: 0729083789 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address5(View vv)  {
        // Dr.KhanJan
        double lat = 36.724899;
        double lng = 67.106773;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک الترسوند حیات" +
                        "\n" +
                        "تداوی: نسائی ولادی" +
                        "\n" +
                        "تماس: 0700505451 ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address6(View vv)  {
        // Dr.KhanJan
        double lat = 36.721180;
        double lng = 67.107647;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک آریایان" +
                        "\n" +
                        "تداوی: امراض داخله و عمومی" +
                        "\n" +
                        "تماس:620230 0789 ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address7(View vv)  {
        // Dr.KhanJan
        double lat = 36.721412;
        double lng = 67.107650;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک ایکو کاردیوگرافی امان" +
                        "\n" +
                        "تداوی: داخله عمومی" +
                        "\n" +
                        "تماس: 0799283830 ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address8(View vv)  {
        // Dr.KhanJan
        double lat = 36.721056;
        double lng = 67.107693;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک نوری" +
                        "\n" +
                        "تداوی: داخله عمومی" +
                        "\n" +
                        "تماس: 0795612958 ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address9(View vv)  {
        // Dr.KhanJan
        double lat = 36.719726;
        double lng = 67.108082;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک تشخصیه ام البلاد" +
                        "\n" +
                        "تداوی: داخله عمومی" +
                        "\n" +
                        "تماس: 0700504180 ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address10(View vv)  {
        // Dr.KhanJan
        double lat = 36.718823;
        double lng = 67.108307;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک و لابراتور طلوع" +
                        "\n" +
                        "تداوی: داخله عمومی" +
                        "\n" +
                        "تماس: 0787379740 ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address11(View vv)  {
        // Dr.KhanJan
        double lat = 36.718648;
        double lng = 67.108339;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک و اکسریز نسیم" +
                        "\n" +
                        "تداوی: اکسریز و رادیولوژی" +
                        "\n" +
                        "تماس: 0700502578 ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address12(View vv)  {
        // Dr.KhanJan
        double lat = 36.718648;
        double lng = 67.108339;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک سلام طبی لابراتور" +
                        "\n" +
                        "تداوی: هماتولوژی, هستولوژی ..." +
                        "\n" +
                        "تماس: 0744150233 ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address13(View vv)  {
        // Dr.KhanJan
        double lat = 36.717107;
        double lng = 67.106397;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک منور" +
                        "\n" +
                        "تداوی: داخله عمومی و معاینات تلویزیونی" +
                        "\n" +
                        "تماس: 0799028731 ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address14(View vv)  {
        // Dr.KhanJan
        double lat = 36.717208;
        double lng = 67.104708;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک تشخصیه اندسکوپی تابش" +
                        "\n" +
                        "تداوی: داخله عمومی" +
                        "\n" +
                        "تماس: 0797190790 ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address15(View vv)  {
        // Dr.KhanJan
        double lat = 36.716330;
        double lng = 67.103492;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک و اکسریز فیضی" +
                        "\n" +
                        "تداوی: اکسریز" +
                        "\n" +
                        "تماس: 0795292979 ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address16(View vv)  {
        // Dr.KhanJan
        double lat = 36.716018;
        double lng = 67.102152;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک دندان غفوری" +
                        "\n" +
                        "تداوی: دهان و دندان" +
                        "\n" +
                        "تماس: 0786523209 ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address17(View vv)  {
        // Dr.KhanJan
        double lat = 36.712695;
        double lng = 67.112214;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک دندان داکتر فیروز ذکا" +
                        "\n" +
                        "تداوی: دهان و دندان" +
                        "\n" +
                        "تماس: 0700555420 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address18(View vv)  {
        // Dr.KhanJan
        double lat = 36.713012;
        double lng = 67.112129;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک تشخصیه و معالجوی آرین افغان" +
                        "\n" +
                        "تداوی: امراض گرده" +
                        "\n" +
                        "تماس: 0780235000  ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address19(View vv)  {
        // Dr.KhanJan
        double lat = 36.716517;
        double lng = 67.132475;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک معالجوی پیام" +
                        "\n" +
                        "تداوی: داخله عمومی و روان اعصاب" +
                        "\n" +
                        "تماس: 0797450300  ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address20(View vv)  {
        // Dr.KhanJan
        double lat = 36.707451;
        double lng = 67.088331;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }

        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("کلینیک OPD داکتر نبیل" +
                        "\n" +
                        "تداوی: داخله عمومی" +
                        "\n" +
                        "تماس: 0700500880  ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }

}