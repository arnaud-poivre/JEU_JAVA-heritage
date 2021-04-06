package fr.pgah.libgdx;

import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Intro extends ApplicationAdapter {

  int NB_SPRITES = 10;
  SpriteBatch batch;
  int longueurFenetre;
  int hauteurFenetre;
  ArrayList<Protagoniste> protagonistes;
  // Joueur joueur;
  boolean gameOver;
  Texture gameOverTexture;
  Thread ajout;

  @Override
  public void create() {
    batch = new SpriteBatch();
    longueurFenetre = Gdx.graphics.getWidth();
    hauteurFenetre = Gdx.graphics.getHeight();
    protagonistes = new ArrayList<>();

    gameOver = false;
    gameOverTexture = new Texture("game_over.png");

    initialisationSprites();
    initialiserJoueur();
  }

  private void initialisationSprites() {

    for (int i = 0; i < NB_SPRITES; i++) {
      protagonistes.add(new Sprite("chien.png"));
    }

    /*
     * ajout = new Thread(new Runnable() { ArrayList<Sprite> s = sprites; int nb =
     * NB_SPRITES;
     * 
     * public void run() { try { for (int i = 0; i < nb; i++) { s.add(new
     * Sprite("chien.png")); Thread.sleep(5000); }
     * 
     * } catch (Exception e) { e.printStackTrace(); }
     * 
     * } });ajout.start();
     */

  }

  private void initialiserJoueur() {
    // joueur = new Joueur();
    protagonistes.add(new Joueur());
  }

  @Override
  public void render() {
    // gameOver est mis à TRUE dès qu'un "hit" est repéré
    if (!gameOver) {
      reinitialiserArrierePlan();
      majEtatProtagonistes();
      majEtatJeu();
      dessiner();
    }
  }

  private void reinitialiserArrierePlan() {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  private void majEtatProtagonistes() {

    for (Protagoniste p : protagonistes) {
      p.majEtat();
    }

    // Sprites
    // for (Sprite sprite : sprites) {
    // sprite.majEtat();
    // }

    // Joueur
    // joueur.majEtat();
  }

  private void majEtatJeu() {
    // On vérifie si le jeu continue ou pas
    if (joueur.estEnCollisionAvec(sprites)) {
      gameOver = true;
    }
  }

  private void dessiner() {
    batch.begin();
    if (gameOver) {
      // cet affichage GAME OVER ne se fera qu'une fois
      // à la fin de la dernière frame au moment du "hit"
      // puisqu'ensuite le render ne fera plus rien
      batch.draw(gameOverTexture, 100, 100);
      // ajout.stop();
    } else {
      // Affichage "normal", jeu en cours
      // for (Sprite sprite : sprites) {
      // sprite.dessiner(batch);
      // }
      // joueur.dessiner(batch);

      for (Protagoniste p : protagonistes) {

        p.dessiner(batch);

      }
    }
    // ajout.stop();
    batch.end();
  }
}
