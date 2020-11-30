package com.tools.cchttp.api;

/**
 * @author AlienChao
 * @date 2019/12/31 10:03
 */
public class UpdateBean {

    /**
     * name : 移动办公平台
     * version : 1
     * changelog : 备注字段限制50
     * updated_at : 1576833814
     * versionShort : 1.0
     * build : 1
     * installUrl : http://download.fir.im/apps/5dd4b1a6b2eb4635f0b37b4e/install?download_token=4559105e9f75f5b3f9a8c536a4b12e33&source=update
     * install_url : http://download.fir.im/apps/5dd4b1a6b2eb4635f0b37b4e/install?download_token=4559105e9f75f5b3f9a8c536a4b12e33&source=update
     * direct_install_url : http://download.fir.im/apps/5dd4b1a6b2eb4635f0b37b4e/install?download_token=4559105e9f75f5b3f9a8c536a4b12e33&source=update
     * update_url : http://fir.im/fcoa
     * binary : {"fsize":18853704}
     */

    private String name;
    private String version;
    private String changelog;
    private int updated_at;
    private String versionShort;
    private String build;
    private String installUrl;
    private String install_url;
    private String direct_install_url;
    private String update_url;
    private BinaryBean binary;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version == null ? "" : version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChangelog() {
        return changelog == null ? "" : changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public String getVersionShort() {
        return versionShort == null ? "" : versionShort;
    }

    public void setVersionShort(String versionShort) {
        this.versionShort = versionShort;
    }

    public String getBuild() {
        return build == null ? "" : build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getInstallUrl() {
        return installUrl == null ? "" : installUrl;
    }

    public void setInstallUrl(String installUrl) {
        this.installUrl = installUrl;
    }

    public String getInstall_url() {
        return install_url == null ? "" : install_url;
    }

    public void setInstall_url(String install_url) {
        this.install_url = install_url;
    }

    public String getDirect_install_url() {
        return direct_install_url == null ? "" : direct_install_url;
    }

    public void setDirect_install_url(String direct_install_url) {
        this.direct_install_url = direct_install_url;
    }

    public String getUpdate_url() {
        return update_url == null ? "" : update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }

    public BinaryBean getBinary() {
        return binary;
    }

    public void setBinary(BinaryBean binary) {
        this.binary = binary;
    }

    public static class BinaryBean {
        /**
         * fsize : 18853704
         */

        private int fsize;

        public int getFsize() {
            return fsize;
        }

        public void setFsize(int fsize) {
            this.fsize = fsize;
        }
    }
}
