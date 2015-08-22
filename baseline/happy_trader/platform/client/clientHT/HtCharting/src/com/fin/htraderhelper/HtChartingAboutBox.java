/*
 * HtChartingAboutBox.java
 */

package com.fin.htraderhelper;

import org.jdesktop.application.Action;

public class HtChartingAboutBox extends javax.swing.JDialog {

    public HtChartingAboutBox(java.awt.Frame parent) {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(closeButton);
    }

    @Action public void closeAboutBox() {
        dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    closeButton = new javax.swing.JButton();
    javax.swing.JLabel appTitleLabel = new javax.swing.JLabel();
    javax.swing.JLabel versionLabel = new javax.swing.JLabel();
    javax.swing.JLabel appVersionLabel = new javax.swing.JLabel();
    javax.swing.JLabel vendorLabel = new javax.swing.JLabel();
    javax.swing.JLabel appVendorLabel = new javax.swing.JLabel();
    javax.swing.JLabel homepageLabel = new javax.swing.JLabel();
    javax.swing.JLabel appHomepageLabel = new javax.swing.JLabel();
    javax.swing.JLabel appDescLabel = new javax.swing.JLabel();
    javax.swing.JLabel imageLabel = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.fin.htraderhelper.HtChartingApp.class).getContext().getResourceMap(HtChartingAboutBox.class);
    setTitle(resourceMap.getString("title")); // NOI18N
    setModal(true);
    setName("aboutBox"); // NOI18N
    setResizable(false);

    javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.fin.htraderhelper.HtChartingApp.class).getContext().getActionMap(HtChartingAboutBox.class, this);
    closeButton.setAction(actionMap.get("closeAboutBox")); // NOI18N
    closeButton.setName("closeButton"); // NOI18N

    appTitleLabel.setFont(appTitleLabel.getFont().deriveFont(appTitleLabel.getFont().getStyle() | java.awt.Font.BOLD, appTitleLabel.getFont().getSize()+4));
    appTitleLabel.setText(resourceMap.getString("Application.title")); // NOI18N
    appTitleLabel.setName("appTitleLabel"); // NOI18N

    versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | java.awt.Font.BOLD));
    versionLabel.setText(resourceMap.getString("versionLabel.text")); // NOI18N
    versionLabel.setName("versionLabel"); // NOI18N

    appVersionLabel.setText(resourceMap.getString("Application.version")); // NOI18N
    appVersionLabel.setName("appVersionLabel"); // NOI18N

    vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | java.awt.Font.BOLD));
    vendorLabel.setText(resourceMap.getString("vendorLabel.text")); // NOI18N
    vendorLabel.setName("vendorLabel"); // NOI18N

    appVendorLabel.setText(resourceMap.getString("Application.vendor")); // NOI18N
    appVendorLabel.setName("appVendorLabel"); // NOI18N

    homepageLabel.setFont(homepageLabel.getFont().deriveFont(homepageLabel.getFont().getStyle() | java.awt.Font.BOLD));
    homepageLabel.setText(resourceMap.getString("homepageLabel.text")); // NOI18N
    homepageLabel.setName("homepageLabel"); // NOI18N

    appHomepageLabel.setText(resourceMap.getString("Application.homepage")); // NOI18N
    appHomepageLabel.setName("appHomepageLabel"); // NOI18N

    appDescLabel.setText(resourceMap.getString("appDescLabel.text")); // NOI18N
    appDescLabel.setName("appDescLabel"); // NOI18N

    imageLabel.setIcon(resourceMap.getIcon("imageLabel.icon")); // NOI18N
    imageLabel.setName("imageLabel"); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(appDescLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
              .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(closeButton)))
            .addContainerGap())
          .addGroup(layout.createSequentialGroup()
            .addGap(36, 36, 36)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(versionLabel)
                  .addComponent(vendorLabel)
                  .addComponent(homepageLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(appHomepageLabel)
                  .addComponent(appVendorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                  .addComponent(appVersionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
              .addComponent(appTitleLabel, javax.swing.GroupLayout.Alignment.LEADING))
            .addGap(184, 184, 184))))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(appTitleLabel)
            .addGap(12, 12, 12)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(versionLabel)
              .addComponent(appVersionLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(vendorLabel)
              .addComponent(appVendorLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(homepageLabel)
              .addComponent(appHomepageLabel))
            .addGap(31, 31, 31)
            .addComponent(appDescLabel)
            .addGap(42, 42, 42)
            .addComponent(closeButton))
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(imageLabel)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents
    
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton closeButton;
  // End of variables declaration//GEN-END:variables
    
}
