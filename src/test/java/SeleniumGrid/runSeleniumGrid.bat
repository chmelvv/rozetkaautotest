:: Delete all drivers processes
taskkill /F /IM chromedriver.exe
taskkill /F /IM geckodriver.exe

:: Start selenium grid hub and node from scirpt
cd C:\Users\vchmel\programs\selenium-server
start "Selenium hub" java -jar selenium-server-standalone-3.141.59.jar -role hub -port 3333

start "Selenium node" java -Dwebdriver.gecko.driver="C:\Users\vchmel\programs\WebDriverGeckoFirefox\geckodriver.exe" -Dwebdriver.chrome.driver="C:\Users\vchmel\programs\WebDriverChrome73\chromedriver.exe" -jar selenium-server-standalone-3.141.59.jar -role node -hub  http://127.0.0.1:3333/grid/register/

:: java -jar selenium-server-standalone-3.141.59.jar --debug -host 127.0.0.1 -role hub -port 3333
::  java -Dwebdriver.gecko.driver="C:\Users\vchmel\programs\WebDriverGeckoFirefox\geckodriver.exe" -Dwebdriver.chrome.driver="C:\Users\vchmel\programs\WebDriverChrome73\chromedriver.exe" -jar selenium-server-standalone-3.141.59.jar -role node -hub  http://127.0.0.1:3333/grid/register/ -host 127.0.0.1