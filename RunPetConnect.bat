@echo off

echo ==============================
echo       PetConnect
echo ==============================
echo.

cd /d "%~dp0src"

echo Compiling PetConnect...
echo.

javac -cp ".;..\lib\mysql-connector-j-26.7.0.jar" ^
model\User.java ^
model\Pet.java ^
model\PetOwner.java ^
interfaces\Adoptable.java ^
service\PetManager.java ^
service\AdoptionService.java ^
dao\UserDAO.java ^
dao\PetDAO.java ^
dao\AdoptionDAO.java ^
util\DatabaseConnection.java ^
gui\LoginFrame.java ^
gui\RegisterFrame.java ^
gui\DashboardFrame.java ^
gui\PetListFrame.java ^
gui\AddPetFrame.java ^
gui\AdoptPetFrame.java ^
gui\AdoptionHistoryFrame.java

if %errorlevel% neq 0 (
    echo.
    echo Compilation failed.
    echo Please check the error above.
    pause
    exit /b
)

echo.
echo Compilation successful!
echo Starting PetConnect...
echo.

java -cp ".;..\lib\mysql-connector-j-26.7.0.jar" gui.LoginFrame

pause