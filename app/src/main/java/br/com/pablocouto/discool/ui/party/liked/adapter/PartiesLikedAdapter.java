package br.com.pablocouto.discool.ui.party.liked.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.pablocouto.discool.R;
import br.com.pablocouto.discool.model.party.Party;

/**
 * Created by Pablo on 26/03/2017.
 */

public class PartiesLikedAdapter extends RecyclerView.Adapter<PartiesLikedAdapter.MyViewHolder> {

    private TouchListenerPartyLiked mTouchParty;
    private List<Party> partyList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageParty;
        TextView titleDescription;
        TextView description;
        TextView labellocation;
        ImageView btnLocation;
        TextView labeldate;
        ImageView btnDate;
        TextView labelTickets;
        ImageView btnTicket;
        ImageView btnDelete;
        ImageView btnShare;
        ImageView btnDislike;

        public MyViewHolder( View view ) {
            super(view);
            this.imageParty = view.findViewById(R.id.image_view_item);
            this.titleDescription = view.findViewById(R.id.label_title_description_item);
            this.description = view.findViewById(R.id.label_desc_description_item);
            this.labellocation = view.findViewById(R.id.label_location_item);
            this.btnLocation = view.findViewById(R.id.btn_location_item);
            this.labeldate = view.findViewById(R.id.label_date_item);
            this.btnDate = view.findViewById(R.id.btn_date_item);
            this.labelTickets = view.findViewById(R.id.label_tickets_item);
            this.btnTicket = view.findViewById(R.id.btn_ticket_item);
            this.btnDelete = view.findViewById(R.id.btn_delete_item);
            this.btnShare = view.findViewById(R.id.btn_share_item);
            this.btnDislike = view.findViewById(R.id.btn_dislike_item);

            this.btnLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mTouchParty.onClickBtnLocation(view, position);
                }
            });
            this.btnDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mTouchParty.onClickBtnDate(view, position);
                }
            });
            this.btnTicket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mTouchParty.onClickBtnTicket(view, position);
                }
            });
            this.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mTouchParty.onClickBtnDelete(view, position);
                }
            });
            this.btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mTouchParty.onClickBtnShare(view, position);
                }
            });
            this.btnDislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mTouchParty.onClickBtnDislike(view, position);
                }
            });
        }
    }

    public PartiesLikedAdapter(List<Party> parties, Context context, TouchListenerPartyLiked callback) {
        this.partyList = parties;
        this.mContext = context;
        this.mTouchParty = callback;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate(R.layout.item_party_liked, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Party party = partyList.get(position);

        holder.titleDescription.setText(party.getTitle());
        holder.description.setText(party.getDescription());
        holder.labellocation.setText(party.getAddressDescription());
        holder.labeldate.setText(party.getDate());
        holder.labelTickets.setText(party.getTicketsDescription());
        Picasso.with(mContext).load(party.getThumb()).placeholder(R.drawable.no_image).into(holder.imageParty);
    }

    @Override
    public int getItemCount() {
        return partyList.size();
    }
}
