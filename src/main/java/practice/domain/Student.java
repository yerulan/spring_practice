package practice.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student implements Serializable {
  
 @Id
 @Column(name = "id")
 @GeneratedValue
 private int id;

 @Column(name = "name")

 private String name;


 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

}