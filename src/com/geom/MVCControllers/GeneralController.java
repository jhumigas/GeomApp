package com.geom.MVCControllers;

import com.geom.MVCModel.Model;
import com.geom.MVCviews.GeneralView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralController {
	private Model model;
	private GeneralView view;
	
	public GeneralController(Model model, GeneralView view){
		this.model=model;
		this.view=view;
		model.addObserver(view);
		view.setVisible(true);
		createEvents();
	}
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
		view.getmntmLoad().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
;
				}
				
		});
		
		view.getmntmSave().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.getBoard().getFigure() !=null && view.getBoard().getFigure().numpoints()>0){
					if(model !=null){
					model.setidFigure(DAOController.returnLastKey(model.getFigure()));
					model.setListKeys();
					view.getForm().getTextField().setText(model.getiDFigure());
					}
				}
			}
		});
		
		view.getbuttonNext().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(view.getTextXString()!=null && view.getTextYString()!=null){
					String coord = "("+view.getTextXString()+","+view.getTextYString()+")";
					model.addPoint(OutputUser.traduction(coord));
				}
			}
		});
		view.getbuttonPrev().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(model.getFigure().numpoints()!=0)
						model.removeLastPt();
					}
		});
		view.getBtnX().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(view.getBoard().getPressedCoord()!=null){
						model.removeOne(model.findInt(view.getBoard().getPressedCoord()));
					}
					}
		});
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
