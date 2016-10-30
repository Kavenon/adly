package pl.edu.agh.student.model;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;

@Table("vendor_profiles")
public class VendorProfiles implements Serializable {

    @PrimaryKey
    private VendorAndProfileKey vendorId;

    public VendorProfiles(VendorAndProfileKey vendorId) {
        this.vendorId = vendorId;
    }

    public VendorAndProfileKey getVendorId() {
        return vendorId;
    }

    public void setVendorId(VendorAndProfileKey vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public String toString() {
        return "VendorProfiles{" +
                "vendorId=" + vendorId +
                '}';
    }
}

