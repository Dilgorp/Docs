package ru.dilgorp.docs

object EndPointsScheme {
    object User {
        private const val root = "/user"
        const val access = "$root/**"

        const val login = "$root/login"
        const val profile = "$root/profile"
    }

    object Document {
        private const val root = "/document"

        object Contract {
            const val root = "${Document.root}/contract"
            const val access = "$root/**"
        }

        object PackingList {
            const val root = "${Document.root}/packing_list"
            const val access = "$root/**"
        }
    }
}