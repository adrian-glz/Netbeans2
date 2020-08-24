/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reportes;

import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;

/**
 *
 * @author Jose Angel
 */
public class BackgroundPanel extends JPanel
{
  Image image;
  public BackgroundPanel(String cImage)
  {
    try
    {
      image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource(cImage), cImage));
    }
    catch (Exception e) {  
        System.out.println("Error al cargar imagen!"); 
    }
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g); 
    if (image != null)
      g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
  }
}

