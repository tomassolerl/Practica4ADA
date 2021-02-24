package com.tomassolerlinares.Practica4.bootstrap;

import com.tomassolerlinares.Practica4.domain.Autor;
import com.tomassolerlinares.Practica4.domain.Editorial;
import com.tomassolerlinares.Practica4.domain.Libro;
import com.tomassolerlinares.Practica4.repository.AutorRepository;
import com.tomassolerlinares.Practica4.repository.EditorialRepository;
import com.tomassolerlinares.Practica4.repository.LibroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AutorRepository autorRepository;
    private final EditorialRepository editorialRepository;
    private final LibroRepository libroRepository;

    public BootStrapData(AutorRepository autorRepository, EditorialRepository editorialRepository, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.editorialRepository = editorialRepository;
        this.libroRepository = libroRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Autor sanderson = new Autor("Brandon Sanderson");

        Editorial nova = new Editorial("Nova CiFi");

        Libro mistborn1 = new Libro("Mistborn: El Imperio Final","Durante mil años han caído cenizas del cielo. Durante mil años nada ha florecido. Durante mil años los skaa han sido esclavizados y viven en la miseria, sumidos en un miedo inevitable...",nova,sanderson);
        Libro mistborn2 = new Libro("Mistborn: El Pozo de la Ascensión", "Durante mil años nada ha cambiado: han caído las cenizas, los skaa han sido esclavizados y el Lord Legislador ha dominado el mundo. Pero lo imposible ha sucedido...", nova, sanderson);
        Libro mistborn3 = new Libro("Mistborn: El Héroe de las Eras", "Durante mil años nada ha cambiado: han caído las cenizas, los skaa han sido esclavizados y el Lord Legislador ha dominado el mundo. Kelsier, el «superviviente», el único que ha logrado huir de los Pozos de Hathsin...", nova, sanderson);

        autorRepository.save(sanderson);

        editorialRepository.save(nova);

        libroRepository.save(mistborn1);
        libroRepository.save(mistborn2);
        libroRepository.save(mistborn3);

        sanderson.getLibros().add(mistborn1);
        sanderson.getLibros().add(mistborn2);
        sanderson.getLibros().add(mistborn3);

        nova.getLibros().add(mistborn1);
        nova.getLibros().add(mistborn2);
        nova.getLibros().add(mistborn3);

        autorRepository.save(sanderson);

        editorialRepository.save(nova);

        Autor cixin = new Autor("Cixin Liu");

        Libro supernova = new Libro("La era de la supernova", "Hace ocho años y a ocho años luz de distancia, murió una estrella. Esta noche, una supernova de alta energía finalmente llegará a la Tierra. El cielo brillará cuando esa nueva estrella florezca y, dentro de un año, todos los adultos...", nova, cixin);

        autorRepository.save(cixin);

        libroRepository.save(supernova);

        cixin.getLibros().add(supernova);

        nova.getLibros().add(supernova);

        autorRepository.save(cixin);

        editorialRepository.save(nova);

        Editorial macmillan = new Editorial("Macmillan USA");

        Libro ball_lightning = new Libro("Ball Lightning", "When Chens parents are incinerated before his eyes by a blast of ball lightning, he devotes his life to cracking the secret of this mysterious natural phenomenon. His search takes him to stormy mountaintops, an experimental military...", macmillan, cixin);

        editorialRepository.save(macmillan);

        libroRepository.save(ball_lightning);

        macmillan.getLibros().add(ball_lightning);

        cixin.getLibros().add(ball_lightning);

        editorialRepository.save(macmillan);

        autorRepository.save(cixin);

        Libro mistborn4 = new Libro("Mistborn: Aleación de ley","Han pasado trescientos años desde los acontecimientos narrados en la primera trilogía de la saga, y Scadrial se encuentra ahora cerca de la modernidad: ferrocarriles, canales...",nova,sanderson);
        Libro mistborn5 = new Libro("Mistborn: Sombras de identidad ", "La sociedad de Nacidos de la Bruma ha evolucionado en una fusión de magia y tecnología en la que la economía se expande, la democracia se enfrenta a la corrupción y la religión...", nova, sanderson);
        Libro tormentas3 = new Libro("El Archivo de las Tormentas: Juramentada","La humanidad se enfrenta a una nueva Desolación con el regreso de los Portadores del Vacío, un enemigo tan grande en número como en sed de venganza. La victoria fugaz de los ejércitos alezi...",nova,sanderson);
        Libro mistborn6 = new Libro("Mistborn: Brazales de Duelo","La cuenca de Elendel es un polvorín. El descontento de los trabajadores se suma a las diferencias irreconciliables entre la capital y las demás ciudades de la cuenca; Elendel asegura...",nova,sanderson);
        Libro mistborn7 = new Libro("Mistborn: El Metal perdido", "Otra aventura apretada y emocionante para el noble Waxillian Ladrian mientras lucha por la justicia en una ciudad fantástica atravesada por canales y vías del tren. Una ciudad que ....", nova, sanderson);

        libroRepository.save(mistborn4);
        libroRepository.save(mistborn5);
        libroRepository.save(tormentas3);
        libroRepository.save(mistborn6);
        libroRepository.save(mistborn7);

        sanderson.getLibros().add(mistborn4);
        sanderson.getLibros().add(mistborn5);
        sanderson.getLibros().add(tormentas3);
        sanderson.getLibros().add(mistborn6);
        sanderson.getLibros().add(mistborn7);

        nova.getLibros().add(mistborn4);
        nova.getLibros().add(mistborn5);
        nova.getLibros().add(tormentas3);
        nova.getLibros().add(mistborn6);
        nova.getLibros().add(mistborn7);

        autorRepository.save(sanderson);

        editorialRepository.save(nova);

        Autor araki = new Autor("Hirohiko Araki");

        Editorial ivrea = new Editorial("Editorial Ivrea");

        autorRepository.save(araki);

        editorialRepository.save(ivrea);

        Libro phantom1 = new Libro("Jojo's Bizarre Adventure: Phantom Blood Part 1", "Narra la historia de la familia Joestar a través de las décadas y sus continuos enfrentamientos con el malvado vampiro Dio Brando y su legado. Una de las características más reconocibles de la obra es que está dividida en distintas partes...", ivrea, araki);
        Libro tormentas2 = new Libro("El Archivo de las Tormentas: Palabras Radiantes","Los Caballeros Radiantes deben volver a alzarse. Los antiguos juramentos por fin se han pronunciado. Los hombres buscan lo que se perdió. Temo que la búsqueda los destruya....",nova,sanderson);
        Libro phantom2 = new Libro("Jojo's Bizarre Adventure: Phantom Blood Part 2", "Narra la historia de la familia Joestar a través de las décadas y sus continuos enfrentamientos con el malvado vampiro Dio Brando y su legado. Una de las características más reconocibles de la obra es que está dividida en distintas partes...", ivrea, araki);
        Libro tormentas1 = new Libro("El Archivo de las Tormentas: El Camino de los Reyes","Anhelo los días previos a la Última Desolación. Los días en que los Heraldos nos abandonaron y los Caballeros Radiantes se giraron en nuestra contra. Un tiempo en que....",nova,sanderson);
        Libro phantom3 = new Libro("Jojo's Bizarre Adventure: Phantom Blood Part 3", "Narra la historia de la familia Joestar a través de las décadas y sus continuos enfrentamientos con el malvado vampiro Dio Brando y su legado. Una de las características más reconocibles de la obra es que está dividida en distintas partes...", ivrea, araki);

        libroRepository.save(phantom1);
        libroRepository.save(tormentas2);
        libroRepository.save(phantom2);
        libroRepository.save(tormentas1);
        libroRepository.save(phantom3);

        araki.getLibros().add(phantom1);
        araki.getLibros().add(phantom2);
        araki.getLibros().add(phantom3);
        sanderson.getLibros().add(tormentas2);
        sanderson.getLibros().add(tormentas1);

        autorRepository.save(araki);

        autorRepository.save(sanderson);

        ivrea.getLibros().add(phantom1);
        ivrea.getLibros().add(phantom2);
        ivrea.getLibros().add(phantom3);
        nova.getLibros().add(tormentas2);
        nova.getLibros().add(tormentas1);

        editorialRepository.save(ivrea);

        editorialRepository.save(nova);


        System.out.println("Hay " + libroRepository.count() + " libros, " + autorRepository.count() + " autores y " + editorialRepository.count() + " editoriales.");

    }
}
