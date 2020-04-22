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

public class Balkh_Doctors_Map_Locator extends AppCompatActivity implements OnMapReadyCallback {

    //database
    DarmaanDB mapdb;
    GoogleMap mGoogleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctors_map);
        //database
        mapdb = new DarmaanDB(this);
        setTitle("داکتران");


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

// ADDDRESSING STARTS
    // address locators starts
    // Static Address 1 codes
    public void Address1(View vv)  {
        // Dr.KhanJan
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
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر محمد رفیق ایرگش" +
                        "\n" +
                        "متخصص امراض داخله" +
                        "\n" +
                        "تماس: 0700505903 ");

        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    // static address 2 codes
    public void Address2(View vv)  {
        // Dr.TalebShah
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
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("دوکتورس کریمه وردک" +
                        "\n" +
                        "متخصص نسایی ولادی" +
                        "\n" +
                        "تماس: 0700505903 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    // static address 3 codes
    public void Address3(View vv)  {
        // Dr.SayedZabihUllahKhadim
        double lat = 36.732641;
        double lng = 67.105152;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر بهادر ایرکین");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    // static address 4 codes
    public void Address4(View vv)  {
        
        double lat = 36.732207;
        double lng = 67.104883;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر فضل سبحان رحیمی");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address5(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.717846;
        double lng = 67.100974;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر معراج الدین فاروقی" +
                        "\n" +
                        "پروفیسور ارتوپیدی" +
                        "\n" +
                        "تماس: 0700510800  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address6(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.717846;
        double lng = 67.100974;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر محمد عالم رازقی" +
                        "\n" +
                        "متخصص امراض داخله" );
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address7(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.718542;
        double lng = 67.100765;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("دوکتورس عایشه صالح" +
                        "\n" +
                        "متخصص نسایی ولادی" +
                        "\n" );
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address8(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.718542;
        double lng = 67.100765;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("دوکتورس وحیده عظیمی" +
                        "\n" +
                        "متخصص نسایی ولادی" +
                        "\n");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address9(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.717212;
        double lng = 36.101251;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر عبدالغفار نوریان" +
                        "\n" +
                        "متخصص داخله اطفال" +
                        "\n" +
                        "تماس: 0700515041  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address10(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.717212;
        double lng = 36.101251;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("دوکتورس یاسمین عظیمی نوریان" +
                        "\n" +
                        "متخصص نسایی ولادی" +
                        "\n");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address11(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.717125;
        double lng = 67.100811;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر سید مسعود شاه اکبری" +
                        "\n" +
                        "متخصص داخله عمومی" +
                        "\n" +
                        "تماس: 0799868801  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address12(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.715817;
        double lng = 67.100286;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر نورالله الیاس" +
                        "\n" +
                        "متخصص داخله عمومی" +
                        "\n" +
                        "تماس: 0786103610  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address13(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.715602;
        double lng = 67.099152;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر گل احمد تانش" +
                        "\n" +
                        "تماس: 0700506675   ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address14(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.715602;
        double lng = 67.099152;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر عبدالحسین عزیزی" +
                        "\n" +
                        "متخصص گوش و گلو" +
                        "\n" +
                        "تماس: 0799255976  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address15(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.715602;
        double lng = 67.099152;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر احمد ولید غفوری" +
                        "\n" +
                        "متخصص داخله و اطفال" +
                        "\n" +
                        "تماس: 0799893030  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address16(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.715427;
        double lng = 67.097870;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر حفیظ الله محبی" +
                        "\n" +
                        "اسیستانت پروفیسور روانی و عصبی" +
                        "\n" +
                        "تماس: 0776311303  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address17(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.714321;
        double lng = 67.092286;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر عبدالکبیر صمیمی" +
                        "\n" +
                        "متخصص جراحی عمومی و ویرولوژی" +
                        "\n" +
                        "تماس: 0783302299  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address18(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.714321;
        double lng = 67.092286;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("دوکتورس سکینه رحیمی" +
                        "\n" +
                        "متخصص نسایی ولادی" +
                        "\n" +
                        "تماس: 0786088966  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address19(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.714321;
        double lng = 67.092286;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر امین الله حبیب زاده اندخوئی" +
                        "\n" +
                        "متخصص امراض جلدی" +
                        "\n" +
                        "تماس: 0786985764  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address20(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.707019;
        double lng = 67.097965;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("دوکتورس نادیه جلال" +
                        "\n" +
                        "اسوشیت پروفیسور نسایی ولادی" +
                        "\n" +
                        "تماس: 0796005252  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address21(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.724899;
        double lng = 67.106773;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("دوکتورس نجیبه حیات" +
                        "\n" +
                        "متخصص نسایی ولادی" +
                        "\n" +
                        "تماس: 0701505451  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address22(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.721056;
        double lng = 67.107693;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر سخی نوری" +
                        "\n" +
                        "تماس: 0789529008  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address23(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.719643;
        double lng = 67.107224;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر حمیدالله حبیبی" +
                        "\n" +
                        "تماس: 0700502353  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address24(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.719726;
        double lng = 67.108082;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر محمد حسین همدرد" +
                        "\n" +
                        "متخصص امراض داخله" +
                        "\n" +
                        "تماس: 0700504180  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address25(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.718823;
        double lng = 67.108307;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر سید یوسف حقبین" +
                        "\n" +
                        "متخصص داخله عمومی" +
                        "\n" +
                        "تماس: 0788876684  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address26(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.717080;
        double lng = 67.108173;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر سید امین الله نصرت" +
                        "\n" +
                        "متخصص روان و اعصاب" +
                        "\n" +
                        "تماس: 0786050070  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address27(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.717364;
        double lng = 67.102340;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("دوکتورس راضیه عالمی" +
                        "\n" +
                        "متخصص امراض جلدی و زهروی" +
                        "\n" +
                        "تماس: 0774216756  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address28(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.717364;
        double lng = 67.102340;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر محمد یوسف عالمی" +
                        "\n" +
                        "متخصص داخله و اطفال" +
                        "\n" +
                        "تماس: 0775216756  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address29(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.716306;
        double lng = 67.103294;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر محمد مصطفی غفاری" +
                        "\n" +
                        "متخصص داخله عمومی و اطفال" +
                        "\n" +
                        "تماس: 0700515896  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address30(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.716306;
        double lng = 67.103294;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر محمد ذکریا محوی" +
                        "\n" +
                        "متخصص روان و اعصاب" +
                        "\n" +
                        "تماس: 0788373069  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address31(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.716306;
        double lng = 67.103294;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("دوکتورس گلثوم حسینی" +
                        "\n" +
                        "متخصص نسایی ولادی" +
                        "\n" +
                        "تماس: 0771066477  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address32(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.716306;
        double lng = 67.103294;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر سید غلام حسین رضوی" +
                        "\n" +
                        "متخصص امراض دهان و دندان" +
                        "\n" +
                        "تماس: 0799444575  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address33(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.716306;
        double lng = 67.103294;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر عبدالواحد ارشاد" +
                        "\n" +
                        "متخصص جراحی عمومی" +
                        "\n" +
                        "تماس: 0700504149  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address34(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.716279;
        double lng = 67.102880;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر فضل احمد قیصاری" +
                        "\n" +
                        "متخصص امراض داخله عمومی" +
                        "\n" +
                        "تماس: 0786573099  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address35(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.716018;
        double lng = 67.102152;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر عبدالسمیع متین" +
                        "\n" +
                        "متخصص جراحی و اروتوپیدی" +
                        "\n" +
                        "تماس: 0700510253  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address36(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.716018;
        double lng = 67.102152;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر فضل رحیم هوشمند" +
                        "\n" +
                        "متخصص جراحی گوش و گلو" +
                        "\n" +
                        "تماس: 0700525715  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address37(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.716018;
        double lng = 67.102152;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر فهیم احمدی" +
                        "\n" +
                        "متخصص امراض داخله و اعصاب" +
                        "\n" +
                        "تماس: 0795600613   ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address38(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.701928;
        double lng = 67.113756;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر محمد اشرف روان" +
                        "\n" +
                        "متخصص روان و اعصاب" +
                        "\n" +
                        "تماس: 0502045717  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address39(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.703018;
        double lng = 67.112154;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر محمد صابر شریفی" +
                        "\n" +
                        "تماس: 0700547404  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address40(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.700814;
        double lng = 67.112640;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر محمد شفیع صدیقی" +
                        "\n" +
                        "متخصص امراض داخله" +
                        "\n" +
                        "تماس: 0700506351  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address41(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.699313;
        double lng = 67.112964;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر عمراخان نیازی" +
                        "\n" +
                        "متخصص داخله عمومی" +
                        "\n" +
                        "تماس: 0778089873  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address42(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.698449;
        double lng = 67.114777;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر احمد طارق طنین" +
                        "\n" +
                        "متخصص روان و اعصاب" +
                        "\n" +
                        "تماس: 0700507001  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address43(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.701287;
        double lng = 67.115075;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر میرویس قیومی" +
                        "\n" +
                        "متخصص داخله عمومی" +
                        "\n" +
                        "تماس: 0797951400  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address44(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.713012;
        double lng = 67.112129;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("دوکتورس پلوشه فرحت رسولی" +
                        "\n" +
                        "متخصص دندان" +
                        "\n" +
                        "تماس: 0729787119  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address45(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.716517;
        double lng = 67.132475;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر فریدون لطیفی" +
                        "\n" +
                        "متخصص روان و اعصاب" +
                        "\n" +
                        "تماس: 0799798929  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address46(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.716517;
        double lng = 67.132475;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("دوکتورس شکریه رهین" +
                        "\n" +
                        "متخصص نسایی ولادی" +
                        "\n" +
                        "تماس: 0793662050  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address47(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.723286;
        double lng = 67.115403;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("داکتر عزیز الله نعمتی" +
                        "\n" +
                        "متخصص جراحی عمومی" +
                        "\n" +
                        "تماس: 0786104826  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address48(View vv)  {
        // Dr.Razia Alimi
        double lat = 36.723286;
        double lng = 67.115403;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.fem_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("دوکتورس اناهیتا نعمتی" +
                        "\n" +
                        "متخصص نسایی ولادی" +
                        "\n" +
                        "تماس: 0787104826  ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
}