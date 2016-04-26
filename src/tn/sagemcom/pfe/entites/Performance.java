/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.sagemcom.pfe.entites;

import java.util.Objects;

/**
 *
 * @author Issam BenBelgacem
 */
public abstract class Performance {

    private int id;
    private String label;
    private String _Date;
    private long gateway_id;

    public long getGateway_id() {
        return gateway_id;
    }

    public void setGateway_id(long gateway_id) {
        this.gateway_id = gateway_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDate() {
        return _Date;
    }

    public void setDate(String _date) {
        this._Date = _date;
    }

    @Override
    public String toString() {
        return "Performance{" + "id=" + id + ", label=" + label + ", _Date=" + _Date + ", gateway_id=" + gateway_id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.label);
        hash = 41 * hash + Objects.hashCode(this._Date);
        hash = 41 * hash + Objects.hashCode(this.gateway_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Performance other = (Performance) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (!Objects.equals(this._Date, other._Date)) {
            return false;
        }
        if (!Objects.equals(this.gateway_id, other.gateway_id)) {
            return false;
        }
        return true;
    }
public abstract int Regle();
}
