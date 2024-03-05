INSERT INTO hello_world_translations (language_code, translation) VALUES
                                                                      ('en', 'Hello World'),
                                                                      ('es', '¡Hola Mundo'),
                                                                      ('fr', 'Bonjour le monde'),
                                                                      ('de', 'Hallo Welt'),
                                                                      ('it', 'Ciao mondo'),
                                                                      ('pt', 'Olá Mundo'),
                                                                      ('ru', 'Привет, мир'),
                                                                      ('ja', 'こんにちは世界'),
                                                                      ('ko', '안녕 세상'),
                                                                      ('zh', '你好，世界');

INSERT INTO users (username, password, roles)
VALUES
    ('admin', 'password1', 'ADMIN'),
    ('admin2', 'password2', 'ADMIN'),
    ('user3', 'password3', 'USER'),
    ('user4', 'password4', 'USER'),
    ('user5', 'password5', 'USER');