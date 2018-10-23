package br.com.pablocouto.discool.fakedata;

import java.util.ArrayList;
import java.util.List;

import br.com.pablocouto.discool.model.party.Party;

public class Fake {

        static List<Party> parties = new ArrayList<>();

        public static List<Party> getDatas(){
            parties.clear();

            parties.add(
                    new Party(
                            "iddid",
                            "Mucho loko",
                            "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy",
                            "Diablo",
                            "Rua 136 - setor Marista- go",
                            "maps.google.com/",
                            "ingresso.com.br",
                            "R$15 antecipado R$30 na hr ",
                            "http://cdn.shopify.com/s/files/1/0611/0605/products/WHATEVER-1_grande.jpg?v=1511379342",
                            "2018-06-06",
                            "23:00:00",
                            "1")
            );

            parties.add(
                    new Party(
                            "iddid",
                            "Open the tch...",
                            "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy",
                            "Diablo",
                            "Rua 136 - setor Marista- go",
                            "maps.google.com/",
                            "ingresso.com.br",
                            "R$15 antecipado R$30 na hr ",
                            "https://thumb7.shutterstock.com/display_pic_with_logo/692005/520401049/stock-photo-two-beautiful-young-couples-having-fun-at-new-year-s-party-wearing-party-hats-dancing-and-blowing-520401049.jpg",
                            "2018-06-06",
                            "23:00:00",
                            "1")
            );

            parties.add(
                    new Party(
                            "iddid",
                            "If I wanna know",
                            "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy",
                            "Diablo",
                            "Rua 136 - setor Marista- go",
                            "maps.google.com/",
                            "ingresso.com.br",
                            "R$15 antecipado R$30 na hr ",
                            "https://www.google.com.br/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=0ahUKEwitwuyXjfvXAhVI8CYKHYluBKIQjBwIBA&url=http%3A%2F%2Fwww.dundeesciencecentre.org.uk%2FUploads%2F2017%2F05%2F11%2F1rVvucKB_party-time-logo.jpg&psig=AOvVaw0UkFI36RX0BKHUkQ1ycdb0&ust=1512846130646024",
                            "2018-06-06",
                            "23:00:00",
                            "1")
            );

            parties.add(
                    new Party(
                            "iddid",
                            "Canfraternização muito loka",
                            "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy",
                            "Diablo",
                            "Rua 136 - setor Marista- go",
                            "maps.google.com/",
                            "ingresso.com.br",
                            "R$15 antecipado R$30 na hr ",
                            "http://cdn.shopify.com/s/files/1/0611/0605/products/WHATEVER-1_grande.jpg?v=1511379342",
                            "2018-06-06",
                            "23:00:00",
                            "1")
            );

            parties.add(
                    new Party(
                            "iddid",
                            "Ultima festa do ano",
                            "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy" +
                                    "Festa muito louco com um monte de mardas, drogas e sexo\n open bar de caipirosca e gummy",
                            "Diablo",
                            "Rua 136 - setor Marista- go",
                            "maps.google.com/",
                            "ingresso.com.br",
                            "R$15 antecipado R$30 na hr ",
                            "https://www.google.com.br/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=0ahUKEwixpN-jjfvXAhUI4yYKHatjDZsQjBwIBA&url=https%3A%2F%2Fpublictheater.org%2FGlobal%2F1617_season_thumbnails-party.jpg&psig=AOvVaw0UkFI36RX0BKHUkQ1ycdb0&ust=1512846130646024",
                            "2018-06-06",
                            "23:00:00",
                            "1")
            );

            for (Party p : parties){
                p.setLike();
            }

            return parties;
        }
}


