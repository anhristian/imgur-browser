package edu.cnm.deepdive.imgurbrowser.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.imgurbrowser.R;
import edu.cnm.deepdive.imgurbrowser.model.Gallery;

public class GalleryListAdapter extends RecyclerView.Adapter<GalleryListAdapter.GalleryViewHolder> {

  private final Context context;
  private final Gallery[] galleries;

  public GalleryListAdapter(Context context, Gallery[] galleries) {
    super();
    this.context = context;
    this.galleries = galleries;
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
    return galleries.length;
  }

  //we created a nested class. Package private it is default , so do not say that.
  class GalleryViewHolder extends RecyclerView.ViewHolder {

    //view objects
    private final TextView title;
    private final TextView description;
    private final Spinner imageSpinner;

    public GalleryViewHolder(@NonNull View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.title);
      description = itemView.findViewById(R.id.description);
      imageSpinner = itemView.findViewById(R.id.gallery_search_spinner);  //making connection here with the XML file item_gallery_search
    }
    private void bind(int position) {
      title.setText(galleries[position].getTitle());
      description.setText(galleries[position].getDescription());
    }

  }

}
