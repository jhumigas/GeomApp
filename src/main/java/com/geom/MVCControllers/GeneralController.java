package com.geom.MVCControllers;

import com.geom.MVCModel.Model;
import com.geom.MVCviews.GeneralView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * This class manages the User Interface
 * It is the link between the view and the model
 * @author Dasha
 *
 */
public class GeneralController {
	private Model model;
	private GeneralView view;

	/**
	 * Binds the model to the view
	 * @param model holds the data to manage, the points, coordinates, area
	 * @param view manages how the data are displayed in the interface
	 */
	public GeneralController(Model model, GeneralView view){
		this.model=model;
		this.view=view;
		// We want the view to be notified once the model changes
		model.addObserver(view);
		view.setVisible(true);
		createEvents();
	}

	/**
	 * Prepare all the events binded to the UI for example listeners of the buttons
	 */
	public void createEvents(){
		if(view.getDesktopPane().isShowing() && view.getForm()!=null){
			view.getForm().getSearchBtn().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(view.getForm().getTextField()!=null){
						String id= view.getForm().getTextField().getText();
						model.setFigure(DAOController.searchFigure(id));
					}			
				}
			});
		}

		// Menu load button
		view.getmntmLoad().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
;
				}
				
		});

		// Menu save button
		view.getmntmSave().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.getBoard().getFigure() !=null && view.getBoard().getFigure().numPoints()>0){
					if(model !=null){
					model.setidFigure(DAOController.returnLastKey(model.getFigure()));
					model.setListKeys();
					view.getForm().getTextField().setText(model.getiDFigure());
					}
				}
			}
		});

		// Next button, used to add a point once the coordinate are entered
		view.getbuttonNext().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(view.getTextXString()!=null && view.getTextYString()!=null){
					String coord = "("+view.getTextXString()+","+view.getTextYString()+")";
					model.addPoint(OutputUser.traduction(coord));
				}
			}
		});

		// Prev button, used to remove the last point
		view.getbuttonPrev().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(model.getFigure().numPoints()!=0)
						model.removeLastPt();
					}
		});

		// Remove a select button
		view.getBtnX().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(view.getBoard().getPressedCoord()!=null){
						model.removeOne(model.findInt(view.getBoard().getPressedCoord()));
					}
					}
		});

		// Changes the coordinates of a selected button
		view.getBtnV().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.getBoard().getPressedCoord()!=null)
					if(view.getTextXString()!=null && view.getTextYString()!=null){
						String coord = "("+view.getTextXString()+","+view.getTextYString()+")";
					model.setOnePoint(model.findInt(view.getBoard().getPressedCoord()),OutputUser.traduction(coord));
					}
				}
	});
		
	}
	

}
