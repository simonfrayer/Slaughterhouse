package via.sdj3.slaughterhouse.model;

import java.sql.Timestamp;

public class Animal
{
  private Timestamp date;
  private double weight;
  private int registrationNumber;
  private String origin;

  public Animal(Timestamp date, double weight, int registrationNumber,
      String origin)
  {
    this.date = date;
    this.weight = weight;
    this.registrationNumber = registrationNumber;
    this.origin = origin;
  }

  public void setDate(Timestamp date)
  {
    this.date = date;
  }

  public void setWeight(double weight)
  {
    this.weight = weight;
  }

  public void setRegistrationNumber(int registrationNumber)
  {
    this.registrationNumber = registrationNumber;
  }

  public void setOrigin(String origin)
  {
    this.origin = origin;
  }

  public Timestamp getDate()
  {
    return date;
  }

  public double getWeight()
  {
    return weight;
  }

  public int getRegistrationNumber()
  {
    return registrationNumber;
  }

  public String getOrigin()
  {
    return origin;
  }
}
