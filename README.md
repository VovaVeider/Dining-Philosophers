# Dining-Philosophers

Demonstration of deadlock on the example of the problem of dining philosophers

Файл для запуска - StupidPhilosophers.java

Постановка задачи:
  За круглым столом сидят 5-ро человек (но можно взять любое число больше 1), а между ними разложены вилки (в данном случае 5 шт.) и каждый из них спит произвольное время, затем поднимает одну вилку, а далее другую. Если вилка занята другим философом,то приходится ждать пока он не опустит эту вилку на стол.
  Далее ест, а затем опускает вилки в том же порядке, что и поднимал.
  Если все из философов будут брать левую или правую вилку первой , то может произойти такая ситуация, когда все философы подняли по одной вилке и ждут пока освободится другая.Но этого не произойдет, т.к никто не опустит вилку, пока не возьмет другую и не поест.
  Способ не допустить такую ситуацию - сделать так, чтобы один из них брал первую вилку не по ту же сторону, что и другие, т.е например один сначала поднимает правую, а остальные левую.

  
Моделирование:
  Вилки - разделяемый ресурс.Попытка взять вилку - это захват блокировки, а когда кладем вилку - освобождаем блокировку.Каждый философ - поток, который хочет захватить блокировку.
  WatchDog(его подобие) служит для ограничения времени работы программы, а также оповещения в случае если потоки не проявляют активность в течение определенного времени( не вызывают reset) и завершения работы программы в обоих случаях.


  
