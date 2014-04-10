package com.github.nill14.ttool.jsf.mbean;
 
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.context.annotation.Scope;
  
@Named("mapBean")
@Scope("session")
public class MapBean implements Serializable {  
	
	private static final long serialVersionUID = 1L;

	private MapModel simpleModel;
	
	private Marker marker;
	
	
	private final String PANEL1 = "/WEB-INF/templates/detailPanel1.xhtml";
	private final String PANEL2 = "/WEB-INF/templates/detailPanel2.xhtml";
	private String detailPanel = PANEL1;
	
    public MapBean() {
        simpleModel = new DefaultMapModel();
         
        //Shared coordinates
        LatLng coord1 = new LatLng(36.879466, 30.667648);
        LatLng coord2 = new LatLng(36.883707, 30.689216);
        LatLng coord3 = new LatLng(36.879703, 30.706707);
        LatLng coord4 = new LatLng(36.885233, 30.702323);
         
        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Konyaalti"));
        simpleModel.addOverlay(new Marker(coord2, "Ataturk Parki"));
        simpleModel.addOverlay(new Marker(coord3, "Karaalioglu Parki"));
        simpleModel.addOverlay(new Marker(coord4, "Kaleici"));
    }
 
    public MapModel getSimpleModel() {
        return simpleModel;
    }	
  
    public Marker getMarker() {
		return marker;
	}	
    
    public String getDetailPanel() {
		return detailPanel;
	}
    
    public void onStateChange(StateChangeEvent event) {
        LatLngBounds bounds = event.getBounds();
        int zoomLevel = event.getZoomLevel();
         
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Zoom Level", String.valueOf(zoomLevel)));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Center", event.getCenter().toString()));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "NorthEast", bounds.getNorthEast().toString()));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "SouthWest", bounds.getSouthWest().toString()));
    }
     
    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
         
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        
        if (PANEL1.equals(detailPanel)) {
        	detailPanel = PANEL2;
        } else {
        	detailPanel = PANEL1;
        }
         
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));
    }    
     
    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
} 