package fr.pgah.libgdx;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Sprite extends Protagoniste {

  boolean versLaDroite;
  boolean versLeHaut;
  double facteurTaille;
  float rotation;
  int vitesseRotation;
  Random generateurAleatoire;

  public Sprite(String img) {
    // On pourrait aussi copier tout le contenu de la méthode ici
    initialiser(img);
  }

  private void initialiser(String img) {
    longueurFenetre = Gdx.graphics.getWidth();
    hauteurFenetre = Gdx.graphics.getHeight();

    generateurAleatoire = new Random();
    this.img = new Texture(img);
    facteurTaille = 1;
    vitesse = 1 + generateurAleatoire.nextInt(10);
    rotation = 0;
    vitesseRotation = 5 + generateurAleatoire.nextInt(21);
    versLaDroite = generateurAleatoire.nextBoolean();
    versLeHaut = generateurAleatoire.nextBoolean();
    longueurEffective = (int) (this.img.getWidth() * facteurTaille);
    hauteurEffective = (int) (this.img.getHeight() * facteurTaille);
    coordX = generateurAleatoire.nextInt(longueurFenetre - longueurEffective);
    coordY = generateurAleatoire.nextInt(hauteurFenetre - hauteurEffective);
    zoneDeHit = new Rectangle(coordX, coordY, longueurEffective, hauteurEffective);
  }

  public void pivoter() {
    rotation += vitesseRotation;
  }

  protected void deplacer() {
    if (versLaDroite) {
      coordX += vitesse;
    } else {
      coordX -= vitesse;
    }
    if (versLeHaut) {
      coordY += vitesse;
    } else {
      coordY -= vitesse;
    }

    majZoneDeHit();
  }

  protected void forcerAResterDansLeCadre() {
    // Gestion bordure droite
    if (coordX + longueurEffective > longueurFenetre) {
      coordX = longueurFenetre - longueurEffective;
      versLaDroite = false;
    }

    // Gestion bordure gauche
    if (coordX < 0) {
      coordX = 0;
      versLaDroite = true;
    }

    // Gestion bordures haute
    if (coordY + hauteurEffective > hauteurFenetre) {
      coordY = hauteurFenetre - hauteurEffective;
      versLeHaut = false;
    }

    // Gestion bordure basse
    if (coordY < 0) {
      coordY = 0;
      versLeHaut = true;
    }

    majZoneDeHit();

  }

  protected void dessiner(SpriteBatch batch) {
    batch.draw(img, coordX, coordY, longueurEffective / 2, hauteurEffective / 2, longueurEffective, hauteurEffective, 1,
        1, rotation, 0, 0, img.getWidth(), img.getHeight(), false, false);
  }
}
