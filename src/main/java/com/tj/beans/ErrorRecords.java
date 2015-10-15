package com.tj.beans;

/**
 * Created by LiuFangGuo on 10/12/15.
 * 和数据库中的对象一一对应
 */
public class ErrorRecords extends ErrorsGrep {
    //------normal fields-------
    private String solution_id;
    private String reader;
    private String writer;
    private String shell_code;
    private String prepare_kernel_cred;
    private String commit_creds;
    private String tty_fasync;
    private String ptmx_open;
    private String tty_init_dev;
    private String tty_release;
    private String ptmx_fops;
    private String ptmx_fops_address;
    private String hack_code;
    private String sys_setresuid;
    private String hack_point;

    public String getSolution_id() {
        return solution_id;
    }

    public void setSolution_id(String solution_id) {
        this.solution_id = solution_id;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getShell_code() {
        return shell_code;
    }

    public void setShell_code(String shell_code) {
        this.shell_code = shell_code;
    }

    public String getPrepare_kernel_cred() {
        return prepare_kernel_cred;
    }

    public void setPrepare_kernel_cred(String prepare_kernel_cred) {
        this.prepare_kernel_cred = prepare_kernel_cred;
    }

    public String getCommit_creds() {
        return commit_creds;
    }

    public void setCommit_creds(String commit_creds) {
        this.commit_creds = commit_creds;
    }

    public String getTty_fasync() {
        return tty_fasync;
    }

    public void setTty_fasync(String tty_fasync) {
        this.tty_fasync = tty_fasync;
    }

    public String getPtmx_open() {
        return ptmx_open;
    }

    public void setPtmx_open(String ptmx_open) {
        this.ptmx_open = ptmx_open;
    }

    public String getTty_init_dev() {
        return tty_init_dev;
    }

    public void setTty_init_dev(String tty_init_dev) {
        this.tty_init_dev = tty_init_dev;
    }

    public String getTty_release() {
        return tty_release;
    }

    public void setTty_release(String tty_release) {
        this.tty_release = tty_release;
    }

    public String getPtmx_fops() {
        return ptmx_fops;
    }

    public void setPtmx_fops(String ptmx_fops) {
        this.ptmx_fops = ptmx_fops;
    }

    public String getPtmx_fops_address() {
        return ptmx_fops_address;
    }

    public void setPtmx_fops_address(String ptmx_fops_address) {
        this.ptmx_fops_address = ptmx_fops_address;
    }

    public String getHack_code() {
        return hack_code;
    }

    public void setHack_code(String hack_code) {
        this.hack_code = hack_code;
    }

    public String getSys_setresuid() {
        return sys_setresuid;
    }

    public void setSys_setresuid(String sys_setresuid) {
        this.sys_setresuid = sys_setresuid;
    }

    public String getHack_point() {
        return hack_point;
    }

    public void setHack_point(String hack_point) {
        this.hack_point = hack_point;
    }

    public String getString() {
        return "did=" + this.getDid() +
                ", record_time=" + this.getRecord_time() +
                ", ErrorRecords{" +
                "solution_id='" + solution_id + '\'' +
                ", reader='" + reader + '\'' +
                ", writer='" + writer + '\'' +
                ", shell_code='" + shell_code + '\'' +
                ", prepare_kernel_cred='" + prepare_kernel_cred + '\'' +
                ", commit_creds='" + commit_creds + '\'' +
                ", tty_fasync='" + tty_fasync + '\'' +
                ", ptmx_open='" + ptmx_open + '\'' +
                ", tty_init_dev='" + tty_init_dev + '\'' +
                ", tty_release='" + tty_release + '\'' +
                ", ptmx_fops='" + ptmx_fops + '\'' +
                ", ptmx_fops_address='" + ptmx_fops_address + '\'' +
                ", hack_code='" + hack_code + '\'' +
                ", sys_setresuid='" + sys_setresuid + '\'' +
                ", hack_point='" + hack_point + '\'' +
                '}';
    }
}
