package com.shibuyaxpress.citasmedicasx.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shibuyaxpress.citasmedicasx.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by paulf on 06-Dec-17.
 */

public  class MessageHolder extends RecyclerView.ViewHolder {
    public TextView messageTextView;
    public ImageView messageImageView;
    public TextView messengerTextView;
    public CircleImageView messengerImageView;

    public MessageHolder(View v) {
        super(v);
        messageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
        messageImageView = (ImageView) itemView.findViewById(R.id.messageImageView);
        messengerTextView = (TextView) itemView.findViewById(R.id.messengerTextView);
        messengerImageView = (CircleImageView) itemView.findViewById(R.id.messengerImageView);
    }
}
