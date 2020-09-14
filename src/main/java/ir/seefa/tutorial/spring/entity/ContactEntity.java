package ir.seefa.tutorial.spring.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 14 Sep 2020 T 03:41:47
 */
// 1. define class as Entity class
@Entity
// 2. define table which refers to this class
@Table(name = "contact", schema = "MyDB", catalog = "")
public class ContactEntity {
    private int contactId;
    private String name;
    private String family;
    private Date birthday;
    private String phone;
    private String telephone;
    private String email;
    private String address;

    // 3. Regular annotations to define @Id or @Basic columns and define table column name or other fields metadata info same as nullable or length
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", nullable = false)
    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "family", nullable = true, length = 50)
    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 15)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "telephone", nullable = true, length = 20)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactEntity that = (ContactEntity) o;
        return contactId == that.contactId &&
                Objects.equals(name, that.name) &&
                Objects.equals(family, that.family) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, name, family, birthday, phone, telephone, email, address);
    }
}
