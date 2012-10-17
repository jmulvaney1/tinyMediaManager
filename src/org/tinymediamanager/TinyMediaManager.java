/*
 * Copyright 2012 Manuel Laggner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinymediamanager;

import java.awt.EventQueue;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.PropertyConfigurator;
import org.tinymediamanager.ui.MainWindow;

/**
 * The Class TinyMediaManager.
 */
public class TinyMediaManager {

  /**
   * The main method.
   * 
   * @param args
   *          the arguments
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          // Get the native look and feel class name
          String nativeLF = UIManager.getSystemLookAndFeelClassName();

          // Install the look and feel
          UIManager.setLookAndFeel(nativeLF);

          // default console logger
          // BasicConfigurator.configure();
          PropertyConfigurator.configure(TinyMediaManager.class.getResource("log4j.conf"));

          // initialize database
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("tmm.odb");
          Globals.entityManager = emf.createEntityManager();

          // proxy settings
          Globals.settings.setProxy();

          // launch application
          MainWindow window = new MainWindow();

        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, e.getMessage());
        }
      }
    });
  }
}
