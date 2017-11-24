package com.shepherdjerred.civilopedia.civitem.building;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shepherdjerred.civilopedia.R;
import com.shepherdjerred.civilopedia.civitem.CivItemDetailsFragment;

public class BuildingDetailsFragment extends CivItemDetailsFragment {

    private static final String ARG_BUILDING = "mBuilding";
    private Building mBuilding;

    public static BuildingDetailsFragment newInstance(Building building) {
        BuildingDetailsFragment fragment = new BuildingDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_BUILDING, building);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBuilding = getArguments().getParcelable(ARG_BUILDING);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_building_details, container, false);

        if (mBuilding != null) {
            ((TextView) rootView.findViewById(R.id.prereq_tech)).setText("Technology: " + mBuilding.getPrereqTech());
            ((TextView) rootView.findViewById(R.id.prereq_civic)).setText("Civic: " + mBuilding.getPrereqCivic());
            ((TextView) rootView.findViewById(R.id.cost)).setText("Production cost: " + mBuilding.getCost());
            ((TextView) rootView.findViewById(R.id.capital)).setText("City must be capital: " + mBuilding.isCapital());
            ((TextView) rootView.findViewById(R.id.prereq_district)).setText("District: " + mBuilding.getPrereqDistrict());
            ((TextView) rootView.findViewById(R.id.description)).setText("Description: " + mBuilding.getDescription());
            ((TextView) rootView.findViewById(R.id.outer_defense_hit_points)).setText("Hit points: " + mBuilding.getOuterDefenseHitPoints());
            ((TextView) rootView.findViewById(R.id.housing)).setText(String.valueOf("Housing: " + mBuilding.getHousing()));
            ((TextView) rootView.findViewById(R.id.entertainment)).setText("Entertainment: " + mBuilding.getEntertainment());
            ((TextView) rootView.findViewById(R.id.maintenance)).setText("Maintenance: " + mBuilding.getMaintenance());
            ((TextView) rootView.findViewById(R.id.trait_type)).setText("Trait type: " + mBuilding.getTraitType());
            ((TextView) rootView.findViewById(R.id.outer_defense_strength)).setText("Strength: " + mBuilding.getOuterDefenseStrength());
            ((TextView) rootView.findViewById(R.id.citizen_slots)).setText("Citizen slots: " + mBuilding.getCitizenSlots());
            ((TextView) rootView.findViewById(R.id.requires_adj_river)).setText("Adjacent river: " + mBuilding.isRequiresAdjacentRiver());
        }

        return rootView;
    }

}