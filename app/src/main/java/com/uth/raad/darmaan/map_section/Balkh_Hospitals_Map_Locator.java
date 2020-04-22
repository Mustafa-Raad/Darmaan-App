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

public class Balkh_Hospitals_Map_Locator extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("شفاخانه ها");

        if (googleServiceAvailable()) {
            //Toast.makeText(this, "Welcome to Hospitals section", Toast.LENGTH_LONG).show();
            setContentView(R.layout.hospitals_map);

            Toolbar mToolbar = (Toolbar) findViewById(R.id.mapToolbar);
            setSupportActionBar(mToolbar);
            initMap();
        } else {
            // No Google Maps Layout
        }
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

// mother error to self location
        // self location finder code start
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//        }
//        mGoogleMap.setMyLocationEnabled(true);
        // self location finder code ends
    }

    // go to location without zoom start of code
//    private void goToLocation(double v, double lat, double lng) {
//        LatLng ll= new LatLng(lat,lng);
//        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
//        mGoogleMap.moveCamera(update);
//    }
    // go to location without zoom ends of code

    // go to location with zoom ... codes start
    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng ll= new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);
    }
    // go to location with zoom ... codes end

    // location finder codes start
    Marker marker;

//search places codes to show
//    private void setMarker(String locality, double lat, double lng) {
//        // start marking codes, search some where to have a marker there in map
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
//        // marker codes finished here
//    }

    // map option map_view_menu code starts from here
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
    } //end of map option map_view_menu code

// ADDDRESSING STARTS
    // Static Address 1 codes
    public void Address1(View vv)  {
        // Alfalah Hospital
        double lat = 36.715427;
        double lng = 67.097870;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);
        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("شفاخانه محبی" +
                        "\n" +
                        "خدمات درمانی: روان و اعصاب" +
                        "\n" +
                        "تماس: 0778081001 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address2(View vv)  {
        // Alfalah Hospital
        double lat = 36.713856;
        double lng = 67.087287;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);
        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("شفاخانه بین المللی مولانا" +
                        "\n" +
                        "خدمات درمانی: اکثر بخش ها" +
                        "\n" +
                        "تماس: 0728464646 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address3(View vv)  {
        // Alfalah Hospital
        double lat = 36.721830;
        double lng = 67.107516;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);
        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("شفاخانه صد بستر نظامی (اردوی ملی)" );
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address4(View vv)  {
        // Alfalah Hospital
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
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("شفاخانه حکیمی" );
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address5(View vv)  {
        // Alfalah Hospital
        double lat = 36.719505;
        double lng = 67.108089;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);
        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("شفاخانه صحت خانواده" +
                        "\n" +
                        "خدمات درمانی: نسائی ولادی" +
                        "\n" +
                        "مسئول شفاخانه: دوکتورس نسرین صادق خجندی" +
                        "\n" +
                        "تماس: 0788504444 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address6(View vv)  {
        // Alfalah Hospital
        double lat = 36.717025;
        double lng = 67.107478;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);
        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("شفاخانه و کلینیک اطفال ولایت بلخ" +
                        "\n" +
                        "خدمات درمانی: معاینات عمومی اطفال و واکسین" );
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address7(View vv)  {
        // Alfalah Hospital
        double lat = 36.716703;
        double lng = 67.105691;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);
        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("شفاخانه حیات بلخ-هند" +
                        "\n" +
                        "خدمات درمانی: اکثر بخش ها" +
                        "\n" +
                        "تماس: 0729212141 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address8(View vv)  {
        // Alfalah Hospital
        double lat = 36.716151;
        double lng = 67.101750;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);
        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("شفاخانه استوماتولوژی شفا" +
                        "\n" +
                        "خدمات درمانی: دندان و دهان" +
                        "\n" +
                        "تماس: 0799719723 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address9(View vv)  {
        // Alfalah Hospital
        double lat = 36.716217;
        double lng = 67.103678;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);
        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("شفاخانه معالجوی کیشا فارابی" +
                        "\n" +
                        "خدمات درمانی: اکثر بخش ها" +
                        "\n" +
                        "تماس: 0788333390 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address10(View vv)  {
        // Alfalah Hospital
        double lat = 36.715588;
        double lng = 67.108964;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);
        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("شفاخانه حوزوی ولایت بلخ(ابو علی سینا بلخی)" +
                        "\n" +
                        "خدمات درمانی: تمام بخش ها بصورت رایگان" );
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address11(View vv)  {
        // Alfalah Hospital
        double lat = 36.703578;
        double lng = 67.114493;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);
        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("شفاخانه چشم آمون" +
                        "\n" +
                        "خدمات درمانی: چشم و بینائی" +
                        "\n" +
                        "تماس: 0700505555 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address12(View vv)  {
        // Alfalah Hospital
        double lat = 36.713866;
        double lng = 67.111891;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);
        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("شفاخانه چشم نور" +
                        "\n" +
                        "خدمات درمانی: چشم و بینائی" );
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address13(View vv)  {
        // Alfalah Hospital
        double lat = 36.718607;
        double lng = 67.119581;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);
        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("شفاخانه بین المللی رهنورد" +
                        "\n" +
                        "خدمات درمانی: تمام بخش ها" +
                        "\n" +
                        "تماس: 0780797979 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }



}