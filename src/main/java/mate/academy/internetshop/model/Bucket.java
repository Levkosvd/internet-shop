package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long id;
    private Long idUser;
    private List<Item> bucketItems;

    public Bucket(Long idOfUser) {
        idUser = idOfUser;
        bucketItems = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Item> getBucketItems() {
        return bucketItems;
    }

    public void setBucketItems(List<Item> bucketItems) {
        this.bucketItems = bucketItems;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Bucket{" + "id=" + id + ", idUser=" + idUser
                + ", bucketItems=" + bucketItems + '}';
    }
}
