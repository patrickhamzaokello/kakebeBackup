package com.pkasemer.kakebeshoplira.Adapters;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.pkasemer.kakebeshoplira.Models.Product;
import com.pkasemer.kakebeshoplira.MyMenuDetail;
import com.pkasemer.kakebeshoplira.R;
import com.pkasemer.kakebeshoplira.RootActivity;
import com.pkasemer.kakebeshoplira.Utils.GlideApp;
import com.pkasemer.kakebeshoplira.localDatabase.SenseDBHelper;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class HomeSectionedRecyclerViewItemAdapter extends RecyclerView.Adapter<HomeSectionedRecyclerViewItemAdapter.ItemViewHolder> {

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView item_name;
        private final TextView item_price;
        private final TextView item_rating;
        private final ImageView itemimage;
        private final ProgressBar mProgress;

        Button home_st_carttn;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemimage = itemView.findViewById(R.id.product_imageview);
            item_name = itemView.findViewById(R.id.item_name);
            item_rating = itemView.findViewById(R.id.item_rating);
            item_price = itemView.findViewById(R.id.item_price);
            mProgress = itemView.findViewById(R.id.home_product_image_progress);

            home_st_carttn = itemView.findViewById(R.id.home_st_carttn);


        }
    }

    private final Context context;
    private final List<Product> products;
    //    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w150";
    private static final String BASE_URL_IMG = "";

    DrawableCrossFadeFactory factory =
            new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();

    SenseDBHelper db;
    boolean food_db_itemchecker;

    int minteger = 1;
    int totalPrice;

    public static final int MENU_SYNCED_WITH_SERVER = 1;
    public static final int MENU_NOT_SYNCED_WITH_SERVER = 0;

    public HomeSectionedRecyclerViewItemAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_section_item_custom_row_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Product product = products.get(position);

        db = new SenseDBHelper(context);

        food_db_itemchecker = db.checktweetindb(String.valueOf(product.getId()));

        updatecartCount();

        if (food_db_itemchecker) {


            holder.home_st_carttn.setBackground(context.getResources().getDrawable(R.drawable.custom_plus_btn));


        } else {


            holder.home_st_carttn.setBackground(context.getResources().getDrawable(R.drawable.custom_check_btn));


        }


        holder.item_name.setText(product.getName());
        holder.item_rating.setText( "Rating "+ product.getDiscount() + " | "+ "5");
        holder.item_price.setText("Ugx " + NumberFormat.getNumberInstance(Locale.US).format(product.getUnitPrice()));

        Glide
                .with(context)
                .load(BASE_URL_IMG + product.getThumbnailImg())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.mProgress.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.mProgress.setVisibility(View.GONE);
                        return false;
                    }

                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                .centerCrop()
                .transition(withCrossFade(factory))
                .into(holder.itemimage);

        holder.home_st_carttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                food_db_itemchecker = db.checktweetindb(String.valueOf(product.getId()));


                if (food_db_itemchecker) {
//                    db.addTweet(
//                            product.getMenuId(),
//                            product.getMenuName(),
//                            product.getPrice(),
//                            product.getDescription(),
//                            product.getMenuTypeId(),
//                            product.getMenuImage(),
//                            product.getBackgroundImage(),
//                            product.getIngredients(),
//                            product.getMenuStatus(),
//                            product.getCreated(),
//                            product.getModified(),
//                            product.getRating(),
//                            minteger,
//                            MENU_NOT_SYNCED_WITH_SERVER
//                    );



                    holder.home_st_carttn.setBackground(context.getResources().getDrawable(R.drawable.custom_check_btn));

                    updatecartCount();


                } else {
                    db.deleteTweet(String.valueOf(product.getId()));

                    holder.home_st_carttn.setBackground(context.getResources().getDrawable(R.drawable.custom_plus_btn));


                    updatecartCount();

                }
            }
        });


        //show toast on click of show all button
        holder.itemimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context.getApplicationContext(), MyMenuDetail.class);
                //PACK DATA
                i.putExtra("SENDER_KEY", "MenuDetails");
                i.putExtra("selectMenuId", product.getId());
                i.putExtra("category_selected_key", product.getCategoryId());
                context.startActivity(i);
            }
        });
    }

    public void switchContent(int id, Fragment fragment) {
        if (context == null)
            return;
        if (context instanceof RootActivity) {
            RootActivity mainActivity = (RootActivity) context;
            Fragment frag = fragment;
            mainActivity.switchContent(id, frag, "MenuDetails");
        }

    }

    private RequestBuilder< Drawable > loadImage(@NonNull String posterPath) {
        return GlideApp
                .with(context)
                .load(BASE_URL_IMG + posterPath)
                .centerCrop();
    }

    private void updatecartCount() {
        db = new SenseDBHelper(context);
        String mycartcount = String.valueOf(db.countCart());
        Intent intent = new Intent(context.getString(R.string.cartcoutAction));
        intent.putExtra(context.getString(R.string.cartCount), mycartcount);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


}