package teoInfo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import teoInfo.model.huffman.ArbolHuffman;
import teoInfo.model.huffman.CodigoHuffman;
import teoInfo.model.huffman.Simbolo;
import teoInfo.model.metricas.Media;
import teoInfo.util.Util;

public class Vista {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Teoria de la Informacion");
		JPanel trabajo = new JPanel(new GridLayout(1,2));
		JPanel analisis = new JPanel(new BorderLayout());
		JButton bAddSimbolo = new JButton( "Agregar Simbolo" );
		JPanel botoneraPanel = new JPanel(new FlowLayout());
		SpringLayout layoutLongMedia = new SpringLayout();
		JPanel longMedia = new JPanel(layoutLongMedia);
		JLabel lLonMedia = new JLabel("<L>: ");
		JPanel metricas = new JPanel(new FlowLayout());
		 JPanel simbolo = new JPanel(new FlowLayout());
		JButton limpiarFuente = new JButton( "Limpiar Fuente" );
		SpringLayout layout = new SpringLayout();
		JPanel simPanel = new JPanel(layout);
		SpringLayout layoutProb = new SpringLayout();
		JPanel probPanel = new JPanel(layoutProb);
		JButton validarFuente = new JButton( "Validar Fuente" );
		JLabel lProb = new JLabel("Probabilidad: ");
        final JTextField tFProb = new JTextField("0.0000",5);
		JLabel lSimb = new JLabel("Simbolo: ");
        final JTextField tFSimb = new JTextField("x",1);
		final JTextArea fuenteLog = new JTextArea("");
		final JTextField lValLonMedia = new JTextField(" ",8);
		lValLonMedia.setEditable(false);
		fuenteLog.setBorder(new TitledBorder(new EtchedBorder(), "Fuente"));
		fuenteLog.setEditable(false);
		final List<Simbolo> fs=new ArrayList();
		final DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
		final JTree arbol = new JTree(rootNode);
		arbol.setBorder(new TitledBorder(new EtchedBorder(), "Huffman"));
		
		JButton botonPrueba = new JButton( "Generar codigos" );
		botonPrueba.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent evt ) {
				
				//String data = new String("Codigo Huffman:\n");
		  		String fuente = new String("Fuente:\n");
		  		if (Util.fValida(fs)){
			  		CodigoHuffman codHuf=new CodigoHuffman();
			  		HashMap<Simbolo,String> codificacion=new HashMap(codHuf.generarCodigoHuffman(fs));
			  		
			  		rootNode.add(dibujarArbol(codHuf.getArbol(),""));
			  		arbol.setShowsRootHandles(true);
			  		for(int i=0; i<fs.size(); i++){
			  			fuente = fuente + fs.get(i).toString()+"\n";
			  		}
			  		String l = Double.toString(Media.calcular(fs, codificacion));
			  		lValLonMedia.setText(l);
			  	} else {
			  		fuente = "Fuente Invalida";
			  	}
		  		fuenteLog.setText(fuente);
		  	}
		} );
		//Panel para configurar la Fuente
		
        tFSimb.selectAll();
		layout.putConstraint(SpringLayout.WEST, lSimb,5,SpringLayout.WEST,simPanel);
        layout.putConstraint(SpringLayout.NORTH, lSimb,5,SpringLayout.NORTH, simPanel);
        
        layout.putConstraint(SpringLayout.WEST, tFSimb,5,SpringLayout.EAST, lSimb);
        layout.putConstraint(SpringLayout.NORTH, tFSimb,5,SpringLayout.NORTH, simPanel);
        
        layout.putConstraint(SpringLayout.EAST, simPanel,5,SpringLayout.EAST, tFSimb);
        layout.putConstraint(SpringLayout.SOUTH, simPanel,5,SpringLayout.SOUTH, tFSimb);
        simPanel.add(lSimb);
        simPanel.add(tFSimb);
        //******Ingreso de Probabilidad*********************
        
        tFProb.selectAll();
		layoutProb.putConstraint(SpringLayout.WEST, lProb,5,SpringLayout.WEST,probPanel);
        layoutProb.putConstraint(SpringLayout.NORTH, lProb,5,SpringLayout.NORTH, probPanel);
        
        layoutProb.putConstraint(SpringLayout.WEST, tFProb,5,SpringLayout.EAST, lProb);
        layoutProb.putConstraint(SpringLayout.NORTH, tFProb,5,SpringLayout.NORTH, probPanel);
        
        layoutProb.putConstraint(SpringLayout.EAST, probPanel,5,SpringLayout.EAST, tFProb);
        layoutProb.putConstraint(SpringLayout.SOUTH, probPanel,5,SpringLayout.SOUTH, tFProb);
        probPanel.add(lProb);
        probPanel.add(tFProb);
        
       
        simbolo.setBorder(new TitledBorder(new EtchedBorder(), "Configuración de la Fuente"));
        
        bAddSimbolo.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent evt ) {
				String  simbolo = tFSimb.getText();
				Double prob = new Double(tFProb.getText());
				Simbolo x=new Simbolo(simbolo.toCharArray()[0],prob.doubleValue());	  		
		  		fs.add(x);
		  		tFSimb.setText("x");
		  		tFSimb.selectAll();
		  		tFProb.setText("0.0000");
		  		tFProb.selectAll();
		  		
		  		String fuente = new String();
				for(int i=0; i<fs.size(); i++){
		  			fuente = fuente + fs.get(i).toString()+"\n";
		  		}
				fuenteLog.setText(fuente);
		  	}
		} );
        simbolo.add(simPanel);
        simbolo.add(probPanel);
        simbolo.add(bAddSimbolo);
        //*****Validar fuente*****************
        
        validarFuente.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent evt ) {
				String fuente = new String();
				for(int i=0; i<fs.size(); i++){
		  			fuente = fuente + fs.get(i).toString()+"\n";
		  		}
				if (Util.fValida(fs)){
					fuente = fuente + "Fuente valida\n";
				} else {
					fuente = fuente + "Fuente Invalida\n";
				}
				fuenteLog.setText(fuente);
		  	}
		} );
        
        limpiarFuente.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent evt ) {
				fs.clear();
				fuenteLog.setText("Se limpio la fuente");
		  	}
		} );
        //****Metricas************************
        
		metricas.setBorder(new TitledBorder(new EtchedBorder(), "Metricas"));
		//***Longitud Media*******************
		layoutLongMedia.putConstraint(SpringLayout.WEST, lLonMedia,5,SpringLayout.WEST,longMedia);
		layoutLongMedia.putConstraint(SpringLayout.NORTH,lLonMedia,5,SpringLayout.NORTH,longMedia);
		layoutLongMedia.putConstraint(SpringLayout.WEST, lValLonMedia,5,SpringLayout.EAST, lLonMedia);
		layoutLongMedia.putConstraint(SpringLayout.NORTH,lValLonMedia,5,SpringLayout.NORTH, longMedia);
		layoutLongMedia.putConstraint(SpringLayout.EAST, longMedia,5,SpringLayout.EAST,lValLonMedia);
		layoutLongMedia.putConstraint(SpringLayout.SOUTH,longMedia,5,SpringLayout.SOUTH,lValLonMedia);
		longMedia.add(lLonMedia);
		longMedia.add(lValLonMedia);
		metricas.add(longMedia);
        //*****Botonera***********************
      	
      	botoneraPanel.add(limpiarFuente);	
        botoneraPanel.add(validarFuente);
        botoneraPanel.add(botonPrueba);
        //************************************
		
		
		analisis.add(new JScrollPane(arbol),BorderLayout.CENTER);
		analisis.add(metricas,BorderLayout.SOUTH);
		
		//Border bordejpanel = new TitledBorder(new EtchedBorder(), "Analisis");
		trabajo.add(new JScrollPane( fuenteLog ));
		trabajo.add(analisis);
		frame.setSize( 700,500 );   
		frame. setLayout( new BorderLayout() );
		frame.getContentPane().add( simbolo,BorderLayout.NORTH );
		frame.getContentPane().add(trabajo,BorderLayout.CENTER );
		frame.getContentPane().add( botoneraPanel,BorderLayout.SOUTH );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	protected static DefaultMutableTreeNode dibujarArbol(ArbolHuffman arbol, String codigo) {
		if (arbol!=null){
			if (arbol.getArbol().getIzq()!=null && arbol.getArbol().getDer()!=null){
				DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(arbol.getProb());
				nodo.add(dibujarArbol(arbol.getArbol().getIzq(),codigo +"0"));
				nodo.add(dibujarArbol(arbol.getArbol().getDer(),codigo +"1"));
				return nodo;
			} else {
				return new DefaultMutableTreeNode(arbol.getSimbolo().toString()+" Codigo:"+codigo);
			}
		} 
		return null;
	}
}
	
	
