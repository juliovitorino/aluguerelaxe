package br.com.jcv.aluguerelaxe.client.componente.date;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

@Deprecated
public class DateAdvancedWidget extends Composite {
	
	private VerticalPanel vp = new VerticalPanel();
	private Label lblDate = new Label();
	private DatePicker datePicker = new DatePicker();
	private boolean lEscondido = false;
	
	public DateAdvancedWidget() {
		initWidget(render());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Widget render() {
		HorizontalPanel hp = new HorizontalPanel() ;
		Image imgCal = new Image("images/16x16/calendar.png");
		datePicker.setVisible(false);
		lblDate.setWidth("120px");
		
		// Inicializa Date Picker
	    datePicker.setValue(new Date(), true);
		
	    // Configura o valor da label quando clicar em alguma data
	    datePicker.addValueChangeHandler(new ValueChangeHandler() {
	      public void onValueChange(ValueChangeEvent event) {
	    	  // A custom date format
	        Date date = (Date) event.getValue();
	        DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
	        String dateString = fmt.format(date);
	        
	        lblDate.setText(dateString);
	      }
	    });
		
		imgCal.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				lEscondido = ! lEscondido;
				datePicker.setVisible( lEscondido );
			}
		});
		
		hp.add(lblDate);
		hp.add(imgCal);
		
		vp.add(hp);
	    vp.add(datePicker);
		
		return vp;
	}
	
	public Date getDate() {
		return new Date(lblDate.getText());
	}
	
	public void setDate(String date) {
		lblDate.setText(date);
	}

}
