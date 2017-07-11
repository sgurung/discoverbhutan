package bt.gov.dit.discoverbhutan.places;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by moic on 10/26/16.
 */

public class PlacesPopulater {

    Place changlimithang, takinzoo, memorialchorten, tashichhodzong,lungtenzampa,moic;
    Marker mChanglimithang, mTakinzoo, mMemorialchorten, mTashichhodzong, mLungtenzampa,mMoIC;

    public PlacesPopulater(){

        changlimithang = new Place(new LatLng(27.471573,89.64098), "Changlimithang","Changlimithang Stadium", mChanglimithang );
       //takinzoo = new Place(new LatLng(27.481801,89.613657), "Motithang Takin Preserve", "Click here more info", mTakinzoo);
        takinzoo = new Place(new LatLng(27.475495, 89.636206), "Motithang Takin Preserve", "Click here more info", mTakinzoo);
        lungtenzampa = new Place(new LatLng(27.475495, 89.636206), "Lungtenzampa Bridge", "Click here more info", mLungtenzampa);
       moic = new Place(new LatLng(27.475495, 89.636206), "Ministry of Information and Communications", "Click here more info", mMoIC);
        memorialchorten = new Place(new LatLng(27.466588,89.637888), "Memorial Chorten", "Memorial Chorten", mMemorialchorten);
        tashichhodzong = new Place(new LatLng(27.48943,89.63502),"Tashichhodzong", "Tashichhodzong",mTashichhodzong);

    }

  public List<Place> getAllPlaces() {


    List<Place> allPlaces = new ArrayList<>();
//      allPlaces.add(changlimithang);
//      Log.d(TAG, "Adding:"+changlimithang.getTitle());
    allPlaces.add(moic);
//      Log.d(TAG, "Adding:"+takinzoo.getTitle());
//      allPlaces.add(memorialchorten);
//      Log.d(TAG, "Adding:"+memorialchorten.getTitle());
//      allPlaces.add(tashichhodzong);
//      Log.d(TAG, "Adding:"+tashichhodzong.getTitle());



      return allPlaces;


 }

}
