package ru.iteye.androidlivecourseapp.data.SocialNetworks

import com.google.gson.Gson
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskSNListener

class APIVK_mock {

    val friendsJSON = "{\"age\":\"27\",\"firstName\":\"Яша\",\"lastName\":\"Радченко\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c841124/v841124944/5229/gjtcIRYO5TM.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Евгений\",\"lastName\":\"Филлипов\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c836130/v836130985/330d2/LFi9KfT0xyo.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Михаил\",\"lastName\":\"Новожилов\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Степан\",\"lastName\":\"Родимов\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Юха\",\"lastName\":\"Юлиаска\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Ольга\",\"lastName\":\"Бекман\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c834102/v834102630/12c422/6rASZSi8ETU.jpg\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Ангелина\",\"lastName\":\"\\u003d)\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"35\",\"firstName\":\"Михаил\",\"lastName\":\"Быконя\",\"like\":false,\"location\":\"Геленджик, Россия\",\"photoSmall\":\"https://pp.userapi.com/c627722/v627722118/2cdf7/XJWZ7GUGpKE.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Игорь\",\"lastName\":\"Финский\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Наталия\",\"lastName\":\"Богданович\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"27\",\"firstName\":\"Паулина\",\"lastName\":\"Антипова\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c844721/v844721154/5cd67/Bpn5zn8z7z8.jpg\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Ольга\",\"lastName\":\"Нарощенко\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c11064/u19842772/d_96ab3e1d.jpg\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Екатерина\",\"lastName\":\"Купцова\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Роман\",\"lastName\":\"Чук\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"28\",\"firstName\":\"Вадим\",\"lastName\":\"Ковелин\",\"like\":false,\"location\":\"Хмельницкий, Украина\",\"photoSmall\":\"https://vk.com/images/camera_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Антон\",\"lastName\":\"Иванов\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"98\",\"firstName\":\"Mercedes\",\"lastName\":\"Benz\",\"like\":false,\"location\":\"Луганск, Украина\",\"photoSmall\":\"https://pp.userapi.com/c4148/u30596187/d_cfcf10af.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Анна\",\"lastName\":\"Петрова\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://sun1-2.userapi.com/c840536/v840536458/8114f/oTgo5ffvvxo.jpg\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Ксюшка\",\"lastName\":\"Μоисеева\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c306103/v306103193/9887/MxDPX_fSKoQ.jpg\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Степан\",\"lastName\":\"Пономарёв\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c834403/v834403760/12dbd6/csSEdi827gk.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Янис\",\"lastName\":\"Кузьмич\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c627218/v627218931/1746/i8YF9DemfU0.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Спанч\",\"lastName\":\"Боб\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c859/u44469384/d_bf355edf.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Казимир\",\"lastName\":\"Соколовский\",\"like\":false,\"location\":\"Екатеринбург, Россия\",\"photoSmall\":\"https://pp.userapi.com/c844521/v844521204/3d6c8/C6CntIcXVMk.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Вадим\",\"lastName\":\"Попов\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c4174/u47394389/d_5a933f6e.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Сергей\",\"lastName\":\"Герасин\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Dobrin\",\"lastName\":\"Georgiev\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Лиза\",\"lastName\":\"Кабанова\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"29\",\"firstName\":\"Дмитрий\",\"lastName\":\"Фролов\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c846416/v846416783/4a211/knKXic3_U40.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"38\",\"firstName\":\"Александр\",\"lastName\":\"Дуда\",\"like\":false,\"location\":\"Энергодар, Украина\",\"photoSmall\":\"https://pp.userapi.com/c636231/v636231093/4eee2/ix9V_RyZQEo.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Серёжка\",\"lastName\":\"Дерганов\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"33\",\"firstName\":\"Владимир\",\"lastName\":\"Макаревич\",\"like\":false,\"location\":\"Санкт-Петербург, Россия\",\"photoSmall\":\"https://pp.userapi.com/c636325/v636325116/366be/17zw1jzXtdo.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Михаил\",\"lastName\":\"Евгеньевич\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://sun1-1.userapi.com/c840739/v840739733/69ee6/ivjKA3UMjew.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Рамин\",\"lastName\":\"Нифдалиев\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Дима\",\"lastName\":\"Кошечкин\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Алексей\",\"lastName\":\"Демидов\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c837623/v837623292/2a216/KsO39hzXFf0.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"21\",\"firstName\":\"Юлия\",\"lastName\":\"Фросина\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c411531/v411531392/961e/0ig93jOQg7k.jpg\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"36\",\"firstName\":\"Андрей\",\"lastName\":\"Медведев\",\"like\":false,\"location\":\"Краснодар, Россия\",\"photoSmall\":\"https://pp.userapi.com/c9746/u151421775/d_98451a32.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Juli\",\"lastName\":\"Kozachek\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c406523/v406523291/cd7f/0sbjNr-X69A.jpg\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Софи\",\"lastName\":\"Лондон\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c317624/v317624720/92f0/YnSjZ1uyzKE.jpg\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Luca\",\"lastName\":\"Mallese\",\"like\":false,\"location\":\"Bologna, Италия\",\"photoSmall\":\"https://pp.userapi.com/c627923/v627923829/71db/1mlc_H0OitA.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"18\",\"firstName\":\"Андрей\",\"lastName\":\"Соболевский\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://vk.com/images/camera_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Люба\",\"lastName\":\"Добрина\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Марек\",\"lastName\":\"Сабиров\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Αндрей\",\"lastName\":\"Βиноградов\",\"like\":false,\"location\":\"Санкт-Петербург, Россия\",\"photoSmall\":\"https://pp.userapi.com/c303311/u183792277/d_8c63bb4c.jpg\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Алихан\",\"lastName\":\"Акбаров\",\"like\":false,\"location\":\"Самарканд, Узбекистан\",\"photoSmall\":\"https://pp.userapi.com/c636922/v636922554/4f7e3/40Ga0luqNN4.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"28\",\"firstName\":\"Анастасия\",\"lastName\":\"Куташева\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c633626/v633626113/1c19c/trDIYnOg16A.jpg\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"DELETED\",\"lastName\":\"\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Александр\",\"lastName\":\"Фан\",\"like\":false,\"location\":\"Минск, Беларусь\",\"photoSmall\":\"https://pp.userapi.com/c623629/v623629235/fdc4/inu25Fi-cP8.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"30\",\"firstName\":\"Виктория\",\"lastName\":\"Нечаева\",\"like\":false,\"location\":\"Санкт-Петербург, Россия\",\"photoSmall\":\"https://pp.userapi.com/c417723/v417723726/c4a/tSUL4Kmb_oI.jpg\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Омер\",\"lastName\":\"Абид\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://pp.userapi.com/c625729/v625729607/1b164/P7_XjuN6Lg8.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Андрей\",\"lastName\":\"Леонтьев\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c312121/v312121366/29e8/fcBEiy9JnmQ.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"30\",\"firstName\":\"Face\",\"lastName\":\"Man\",\"like\":false,\"location\":\"Санкт-Петербург, Россия\",\"photoSmall\":\"https://pp.userapi.com/c624517/v624517197/1b914/LV_eQTvudOg.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"DELETED\",\"lastName\":\"\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"29\",\"firstName\":\"Антон\",\"lastName\":\"Ермолаев\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c638823/v638823105/223fe/5lnsJDIym-A.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Diamond\",\"lastName\":\"Byti\",\"like\":false,\"location\":\"Los Angeles, США\",\"photoSmall\":\"https://pp.userapi.com/c627428/v627428778/93bf/MbnSVoxKqZc.jpg\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"47\",\"firstName\":\"Γеннадий\",\"lastName\":\"Βоронцов\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c625822/v625822633/e247/mHE9LMJneDw.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Дядя\",\"lastName\":\"Фестор\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c302506/v302506976/56c8/IcGgoDkEp5s.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Юрий\",\"lastName\":\"Макаров\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Роман\",\"lastName\":\"Степанов\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c402425/v402425461/d1f6/EgWlZlHZYgw.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Tolga\",\"lastName\":\"Tuncay\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Виолетта\",\"lastName\":\"Бочарова\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Didi\",\"lastName\":\"Nazim\",\"like\":false,\"location\":\"Québec, Канада\",\"photoSmall\":\"https://pp.userapi.com/c637325/v637325449/2e19/WiLlHokDfeM.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"37\",\"firstName\":\"Secret\",\"lastName\":\"Garden\",\"like\":false,\"location\":\"Antalya, Турция\",\"photoSmall\":\"https://pp.userapi.com/c616816/v616816412/893b/l8EjNykMYIQ.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Пара-Половинок\",\"lastName\":\"Москва\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"44\",\"firstName\":\"Денис\",\"lastName\":\"Попов\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c619218/v619218280/979d/NTK1mKF6F1I.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Евгений\",\"lastName\":\"Меньшиков\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c836725/v836725052/37eac/3oSeD5eusAY.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Василина\",\"lastName\":\"Токарева\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Альбина\",\"lastName\":\"Котова\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Вова\",\"lastName\":\"Иванов\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://pp.userapi.com/c622525/v622525529/fd5/QI1v17kEZLs.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Alexander\",\"lastName\":\"Eckhardt\",\"like\":false,\"location\":\"Hamburg, Германия\",\"photoSmall\":\"https://pp.userapi.com/c617530/v617530404/1bb81/vU3hpW5OeO8.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Карапет\",\"lastName\":\"Исраелян\",\"like\":false,\"location\":\"Ереван, Армения\",\"photoSmall\":\"https://pp.userapi.com/c619431/v619431316/1822e/xfxvGiIlbVc.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Old\",\"lastName\":\"Prankish\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c620316/v620316002/13c3a/dafbyrjs21w.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Игнат\",\"lastName\":\"Семенов\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Ангелина\",\"lastName\":\"Ротман\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":1,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Александр\",\"lastName\":\"Дмитриев\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Павел\",\"lastName\":\"Романов\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"36\",\"firstName\":\"Roman\",\"lastName\":\"Penkov\",\"like\":false,\"location\":\"Москва, Россия\",\"photoSmall\":\"https://pp.userapi.com/c629414/v629414017/4138f/rOCco0p0SPY.jpg\",\"sex\":2,\"snType\":\"vk\"}, \n" +
            "{\"age\":\"\",\"firstName\":\"Kayar\",\"lastName\":\"Çakar\",\"like\":false,\"location\":\"\",\"photoSmall\":\"https://vk.com/images/deactivated_200.png\",\"sex\":2,\"snType\":\"vk\"}"


    fun getFriends(listener: TaskSNListener) {
        val gson = Gson()
        val myDataset: ArrayList<Friend> = arrayListOf()

        val arrayOfJSON = friendsJSON.split(", \n")

        var person: Friend
        for (i in arrayOfJSON) {
            person = gson.fromJson(i, Friend::class.java)
            myDataset.add(person)
        }


        listener.onSuccess(myDataset)
    }



}