package edu.cnm.deepdive.imgurbrowser.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.imgurbrowser.R;
import edu.cnm.deepdive.imgurbrowser.model.Gallery;
import edu.cnm.deepdive.imgurbrowser.model.Image;
import java.util.ArrayList;
import java.util.List;

public class GalleryListAdapter extends RecyclerView.Adapter<GalleryListAdapter.GalleryViewHolder> {

  private final Context context;
  private final List<Gallery> galleries;
  private final OnItemSelectedHelper onItemSelectedHelper;

  public GalleryListAdapter(Context context, List<Gallery> galleries,
      OnItemSelectedHelper onItemSelectedHelper) {
    super();
    this.context = context;
    this.galleries = galleries;
    this.onItemSelectedHelper = onItemSelectedHelper;
  }

  @NonNull
  @Override                            //3 methods we have to import.
  public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_gallery_search, parent, false);
    return new GalleryViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull GalleryListAdapter.GalleryViewHolder holder, int position) {
    holder.bind(position);

  }

  @Override
  public int getItemCount() {
    return galleries.size();
  }

  //we created a nested class. Package private it is default , so do not say that.
  class GalleryViewHolder extends RecyclerView.ViewHolder implements OnItemSelectedListener {

    //view objects
    private final TextView title;
    private final TextView description;
    private final Spinner imageSpinner;
    private Gallery gallery;

    private final List<Image> withIconList = new ArrayList<>();
    private final String imageUrl = "" + R.drawable.gallery; //quotes + turn it in a string tge png.
    private final Image galleryIcon = new Image(imageUrl);

    public GalleryViewHolder(@NonNull View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.gallery_title);
      description = itemView.findViewById(R.id.gallery_description);
      imageSpinner = itemView.findViewById(
          R.id.gallery_search_spinner);  //making connection here with the XML file item_gallery_search

    }

    private void bind(int position) {
      gallery = galleries.get(
          position); //go to galleries take a particular gallery (position 0 e.g. and take a particular image)
      withIconList.clear(); //clear it from other information
      withIconList.add(galleryIcon);
      withIconList.addAll(gallery.getImages());
      title.setText(gallery.getTitle());
      description.setText(gallery.getDescription());
      GalleryImageAdapter galleryImageAdapter = new GalleryImageAdapter(context,
          gallery.getImages());
      imageSpinner.setAdapter(galleryImageAdapter);
      imageSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
      if (position > 0) {
        onItemSelectedHelper.onSelected(gallery, gallery.getImages().get(position - 1));
        adapterView.setSelection(0);

      }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
  }

  public interface OnItemSelectedHelper {

    void onSelected(Gallery gallery, Image image);
  }
}




