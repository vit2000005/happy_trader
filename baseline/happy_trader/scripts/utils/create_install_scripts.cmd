@echo off

:: create env.cmd
echo @echo off > dummy_tmp.dat
echo ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: >> dummy_tmp.dat
echo ::GENERATED BY HAPPY TRADER INSTALLER %date% %time% >> dummy_tmp.dat
echo ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: >> dummy_tmp.dat
echo: >> dummy_tmp.dat
echo: >> dummy_tmp.dat


copy /y dummy_tmp.dat+supp\env_tmpl.dat ..\..\install\center\server\supp\env.cmd /b
copy /y dummy_tmp.dat+supp\start_server.dat ..\..\install\center\server\supp\start_server.cmd /b
copy /y dummy_tmp.dat+supp\user_data.dat ..\..\install\center\server\supp\user_data.cmd /b
copy /y dummy_tmp.dat+supp\recreate_tables.dat ..\..\install\center\server\supp\recreate_tables.cmd /b


:: remove temp
del /f /q dummy_tmp.dat