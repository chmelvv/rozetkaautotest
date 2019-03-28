:: Delete all driver processes
taskkill /F /IM chromedriver.exe
taskkill /F /IM geckodriver.exe

:: Start slenium grid hub and node from scirpt
cd C:\Users\vchmel\programs\selenium-server
start "Selenium hub" java -jar selenium-server-standalone-3.141.59.jar -role hub

start "Selenium node" java -Dwebdriver.gecko.driver="C:\Users\vchmel\programs\WebDriverGeckoFirefox\geckodriver.exe" -Dwebdriver.chrome.driver="C:\Users\vchmel\programs\WebDriverChrome73\chromedriver.exe" -jar selenium-server-standalone-3.141.59.jar -role node -hub  http://192.168.1.40:4444/grid/register/