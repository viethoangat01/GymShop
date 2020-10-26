package com.example.gymshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gymshop.R;
import com.example.gymshop.model.ProductType;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductTypeAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ProductType> productTypeList;

    public ProductTypeAdapter(Context context, int layout, List<ProductType> productTypeList) {
        this.context = context;
        this.layout = layout;
        this.productTypeList = productTypeList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return productTypeList.size();
    }

    @Override
    public Object getItem(int i) {
        return productTypeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            viewHolder=new ViewHolder();
            //Ánh xạ View
            viewHolder.imageView=(ImageView) view.findViewById(R.id.imageviewProductType);
            viewHolder.textView=(TextView) view.findViewById(R.id.textviewProductType);
            view.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) view.getTag();
        }
        //Gán giá trị
        ProductType productType=(ProductType) getItem(i);
        if(productType!=null){
            ProductType p=productTypeList.get(i);
            //Picasso.with(getApplicationContext()).load(advertisement.get(i)).into(imageView);
            Picasso.with(context).load(p.getTypeImage())
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_warning)
            .into(viewHolder.imageView);
            viewHolder.textView.setText(p.getTypeName());
        }
        return view;
    }
}
