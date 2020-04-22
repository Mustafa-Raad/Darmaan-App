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

public class Balkh_Drugstores_Map_Locator extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("دواخانه ها(درملتون)");

        if (googleServiceAvailable()) {
            //Toast.makeText(this, "Welcome to Drugstores section", Toast.LENGTH_LONG).show();
            setContentView(R.layout.drugstores_map);

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

//mohter error
        // self location finder code start
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
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


    // Static Address 1 codes
    public void Address1(View vv)  {
        // Dr.KhanJan
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
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("همایون درملتون" +
                        "\n" +
                        "تماس: 0784240027 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address2(View vv)  {
        // Dr.KhanJan
        double lat = 36.732207;
        double lng = 67.104883;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("امید جلیل درملتون" +
                        "\n" +
                        "مسئول: عباس دواساز" +
                        "\n" +
                        "تماس: 0799390210 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address3(View vv)  {
        // Dr.KhanJan
        double lat = 36.717846;
        double lng = 67.100974;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("فاروقی درملتون" +
                        "\n" +
                        "مسئول: میوند" +
                        "\n" +
                        "تماس: 0799888977 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address4(View vv)  {
        // Dr.KhanJan
        double lat = 36.717212;
        double lng = 67.101251;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("امید درملتون" +
                        "\n" +
                        "مسئول: ذکرالله" +
                        "\n" +
                        "تماس: 0788288707 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address5(View vv)  {
        // Dr.KhanJan
        double lat = 36.717125;
        double lng = 67.100811;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("عظیمی درملتون" +
                        "\n" +
                        "مسئول: محمود" +
                        "\n" +
                        "تماس: 0775293443 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address6(View vv)  {
        // Dr.KhanJan
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
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("سمیر درملتون" +
                        "\n" +
                        "مسئول: محمد ظاهر" +
                        "\n" +
                        "تماس: 0798000015 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address7(View vv)  {
        // Dr.KhanJan
        double lat = 36.715602;
        double lng = 67.099152;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("باختر درملتون" +
                        "\n" +
                        "مسئول: متین" +
                        "\n" +
                        "تماس: 0705000777 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address8(View vv)  {
        // Dr.KhanJan
        double lat = 36.714321;
        double lng = 67.092286;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("قیس ظهیر درملتون" +
                        "\n" +
                        "مسئول: قیس" +
                        "\n" +
                        "تماس: 0786088966 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address9(View vv)  {
        // Dr.KhanJan
        double lat = 36.709337;
        double lng = 67.087684;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("محمد آصف درملتون" +
                        "\n" +
                        "مسئول: عبدالخالق" +
                        "\n" +
                        "تماس: 0744881819 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address10(View vv)  {
        // Dr.KhanJan
        double lat = 36.707019;
        double lng = 67.097965;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("علی جوشن درملتون" +
                        "\n" +
                        "مسئول: محمد حسین" +
                        "\n" +
                        "تماس: 0783567241 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address11(View vv)  {
        // Dr.KhanJan
        double lat = 36.719643;
        double lng = 67.107224;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("احمد قیس درملتون" +
                        "\n" +
                        "مسئول: داکتر قیس نایاب" +
                        "\n" +
                        "تماس: 0700509726 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address12(View vv)  {
        // Dr.KhanJan
        double lat = 36.717080;
        double lng = 67.108173;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("بابر سینا درملتون" +
                        "\n" +
                        "تماس: 0799372334 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address13(View vv)  {
        // Dr.KhanJan
        double lat = 36.716306;
        double lng = 67.103294;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("صمیم قادری درملتون" +
                        "\n" +
                        "مسئول: ذبیح الله مرادی" +
                        "\n" +
                        "تماس: 0700522008 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address14(View vv)  {
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
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("فقیر زاده درملتون" +
                        "\n" +
                        "مسئول: جان محمد ضیاء" +
                        "\n" +
                        "تماس: 0700509045 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address15(View vv)  {
        // Dr.KhanJan
        double lat = 36.701928;
        double lng = 67.113756;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("عارف تمیم درملتون" );
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address16(View vv)  {
        // Dr.KhanJan
        double lat = 36.703018;
        double lng = 67.112154;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("رفا درملتون" +
                        "\n" +
                        "تماس: 0700515238 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address17(View vv)  {
        // Dr.KhanJan
        double lat = 36.699313;
        double lng = 67.112964;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("اسلمی درملتون" +
                        "\n" +
                        "تماس: 0796100021 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address18(View vv)  {
        // Dr.KhanJan
        double lat = 36.723286;
        double lng = 67.115403;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("شفاجو درملتون" +
                        "\n" +
                        "تماس: 0782131138 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }
    public void Address19(View vv)  {
        // Dr.KhanJan
        double lat = 36.716584;
        double lng = 67.100949;
        int zoom = 15;

        goToLocationZoom(lat,lng,zoom);

        if (marker != null){
            marker.remove();
        }
        // start marking codes, search some where to have a marker there in map
        MarkerOptions options = new MarkerOptions()
                //.title(locality)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mal_doc_ic))
                .position(new LatLng(lat, lng))
                .snippet("ادویه فروشی دیپوی نمبر13(دولتی)" +
                        "\n" +
                        "مسئول: رحیم الله" +
                        "\n" +
                        "تماس: 0700550567 ");
        marker = mGoogleMap.addMarker(options);
        // marker codes finished here
    }

}