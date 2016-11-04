package pl.edu.agh.student.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("profile_properties")
public class ProfileCard {

    @PrimaryKey
    private ProfileCardKey profilePropertyKey;

    @Column("property_value")
    private String value;

    public ProfileCard(ProfileCardKey profilePropertyKey, String value) {
        this.profilePropertyKey = profilePropertyKey;
        this.value = value;
    }

    public ProfileCardKey getProfilePropertyKey() {
        return profilePropertyKey;
    }

    public void setProfilePropertyKey(ProfileCardKey profilePropertyKey) {
        this.profilePropertyKey = profilePropertyKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
