import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import userInterface.GameSetupUIState;
import userInterface.GameUIState;
import userInterface.TestUI;

import java.awt.Choice;
import java.awt.Component;

import javax.swing.JComboBox;

public class TestRunUI extends JPanel{
	ImageIcon[] images;
    String[] petStrings;

	public static void main(String[] args) {
		//Create a class object
		JFrame frame = new JFrame();
		frame.getContentPane().add(new TestRunUI());
		frame.setPreferredSize(new Dimension(1024,768));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(1024,768));
		frame.setResizable(false);
		
	}
	
	public TestRunUI() {
		
		//Add class panels
		ImageIcon img1 = createImageIcon("avatar.png",200,200);
		JLabel lb1 = new JLabel(img1);
		//lb1.setBounds(10,10, 200,200);
		
		/*
		JPanel p1=new JPanel();
		JLabel l1 = new JLabel("hi help");
		p1.setLayout(new BorderLayout());
		p1.add(l1, BorderLayout.CENTER);
		p1.setPreferredSize(new Dimension(200,200));
		panel.add(p1,BorderLayout.WEST);
		*/
		
		JPanel p1=new JPanel();
		p1.setPreferredSize(new Dimension(1024, 300));
		
		JLabel l1 = new JLabel();
		l1.setText("Hi");
		p1.add(l1);
		p1.setOpaque(false);
		
		setLayout(new BorderLayout());
		add(p1, BorderLayout.NORTH);
		//panel.add(lb1, BorderLayout.CENTER);
		
		JPanel leftP = new JPanel();
		leftP.setPreferredSize(new Dimension(300,768));
		JPanel rightP = new JPanel();
		rightP.setPreferredSize(new Dimension(300,768));
		add(leftP, BorderLayout.WEST);
		add(rightP, BorderLayout.EAST);
		
		JPanel downP = new JPanel();
		downP.setPreferredSize(new Dimension(1024,200));
		add(downP, BorderLayout.SOUTH);
		
		
		
	    petStrings = new String[]{"avatar", "avatarr", "logo", "profile"};
	    images = new ImageIcon[petStrings.length];
        Integer[] intArray = new Integer[petStrings.length];
        for (int i = 0; i < petStrings.length; i++) {
            intArray[i] = new Integer(i);
            images[i] = createImageIcon(petStrings[i] + ".png",200,200);
            if (images[i] != null) {
                images[i].setDescription(petStrings[i]);
            }
        }

        //Create the combo box.
        JComboBox petList = new JComboBox(intArray);
        ComboBoxRenderer renderer= new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(200, 200));
        petList.setRenderer(renderer);
        petList.setMaximumRowCount(3);

        //Lay out the demo.
        add(petList, BorderLayout.CENTER);
      
        //  setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		

		/*
		choice.add("apple");
		choice.add("pine");
		 */
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon bgIcon = createImageIcon("bg.png",1024, 768);
		Image img = bgIcon.getImage();
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(img, 0, 0, 1024, 768, this);
		
	}
	class ComboBoxRenderer extends JLabel implements ListCellRenderer {
		private Font uhOhFont;
		public ComboBoxRenderer() {
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
		}
		
		/*
		* This method finds the image and text corresponding
		* to the selected value and returns the label, set up
		* to display the text and image.
		*/
		public Component getListCellRendererComponent(JList list, Object value,int index,boolean isSelected, boolean cellHasFocus) {
		//Get the selected index. (The index param isn't
		//always valid, so just use the value.)
		int selectedIndex = ((Integer)value).intValue();
		
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		
		//Set the icon and text.  If icon was null, say so.
		ImageIcon icon = images[selectedIndex];
		String pet = petStrings[selectedIndex];
		setIcon(icon);
		if (icon != null) {
		setText(pet);
		setFont(list.getFont());
		} else {
		setUhOhText(pet + " (no image available)",
		     list.getFont());
		}
		
		return this;
		}
		
		//Set the font and text when no image was found.
		protected void setUhOhText(String uhOhText, Font normalFont) {
		if (uhOhFont == null) { //lazily create this font
		uhOhFont = normalFont.deriveFont(Font.ITALIC);
		}
		setFont(uhOhFont);
		setText(uhOhText);
		}
		}

	
	     
	public static ImageIcon createImageIcon(String path, int width, int height) {
		Image img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
		}
		
		Image resizedImage = img.getScaledInstance(width, height, 0);
		return new ImageIcon(resizedImage);
	}

}
