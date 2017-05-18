/**
 * (The MIT License)
 *
 * Copyright 2015-2016 Knowm Inc. (http://knowm.org) and contributors.
 * Copyright 2013-2015 Xeiam LLC (http://xeiam.com) and contributors.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.knowm.datasets.samples;

import javax.swing.JPanel;

import org.knowm.datasets.mnist.Mnist;
import org.knowm.datasets.mnist.MnistDAO;
import org.knowm.datasets.mnist.tools.MnistDigitViewer;
import org.knowm.datasets.mnist.tools.MnistImagePanel;

/**
 * @author alexnugent
 */
public class MnistImageDisplayApp {

  /**
   * This app takes the following arguments:
   * <ul>
   * <li>int image index (0): Image index [0-69,999]
   * 
   * @param args
   */
  public static void main(String[] args) {

    try {
      MnistDAO.init("/usr/local/Datasets"); // setup data
      MnistImageDisplayApp mnistImageDisplayApp = new MnistImageDisplayApp();
      mnistImageDisplayApp.go(args);
    } catch (Exception e) {
      // eat it.
    } finally {
      MnistDAO.release(); // release data resources
    }

  }

  public void go(String[] args) {

    int imageIndex = 0;

    try {
      imageIndex = Integer.parseInt(args[0]);

    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // just ignore
    }

    Mnist mnist = MnistDAO.selectSingle(imageIndex);

    int[][] img = mnist.getImageMatrix();

    // paint the patches
    JPanel mnistImagePanel = new MnistImagePanel(img, 8);
    new MnistDigitViewer(mnistImagePanel, "Index = " + mnist.getId() + " label = " + mnist.getLabel());

  }
}
